# ─────────────────────────────────────────────────────────────────────────────
# Stage 1 — BUILD
# Compila el proyecto y genera el jar ejecutable usando Maven wrapper.
# ─────────────────────────────────────────────────────────────────────────────
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /workspace

# Copia solo el descriptor de dependencias primero para aprovechar la caché de
# capas de Docker: si pom.xml no cambia, las dependencias no se re-descargan.
COPY pom.xml .
COPY .mvn/ .mvn/
COPY mvnw .
RUN chmod +x mvnw && ./mvnw dependency:go-offline -q

# Copia el código fuente y construye el jar (sin tests — se ejecutan en CI).
COPY src/ src/
RUN ./mvnw package -DskipTests -q

# ─────────────────────────────────────────────────────────────────────────────
# Stage 2 — RUNTIME
# Imagen final ligera: solo JRE + el jar. Sin Maven, sin código fuente.
# ─────────────────────────────────────────────────────────────────────────────
FROM eclipse-temurin:17-jre-alpine AS runtime

WORKDIR /app

# Usuario no-root para seguir buenas prácticas de seguridad en contenedores.
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Copia únicamente el jar desde la etapa de build.
COPY --from=build /workspace/target/users-management-*.jar app.jar

# Puerto en el que escucha Spring Boot (debe coincidir con server.port).
EXPOSE 8080

# Opciones de JVM recomendadas para contenedores:
#   -XX:+UseContainerSupport  → respeta los límites de CPU/RAM del contenedor
#   -XX:MaxRAMPercentage=75   → usa máximo el 75 % de la RAM asignada al contenedor
ENTRYPOINT ["java", \
  "-XX:+UseContainerSupport", \
  "-XX:MaxRAMPercentage=75.0", \
  "-jar", "app.jar"]
