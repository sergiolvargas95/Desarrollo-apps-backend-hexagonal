package com.jcaa.usersmanagement.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de OpenAPI / Swagger UI.
 *
 * <p>Define el esquema de seguridad {@code bearerAuth} (JWT sobre HTTP) y lo aplica globalmente para
 * que Swagger UI muestre el botón <strong>Authorize</strong>. Allí se pega el token JWT obtenido de
 * {@code POST /api/auth/login} y Swagger lo envía automáticamente en la cabecera
 * {@code Authorization: Bearer <token>} en todas las peticiones posteriores.
 */
@Configuration
@OpenAPIDefinition(
    info =
        @Info(
            title = "Users Management API",
            version = "2.0.0",
            description =
                "API de gestión de usuarios y cursos con arquitectura hexagonal y DDD. "
                    + "Autentíquese en POST /api/auth/login para obtener un token JWT y "
                    + "úselo en el botón Authorize."),
    security = @SecurityRequirement(name = "bearerAuth"))
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer")
public class OpenApiConfig {}
