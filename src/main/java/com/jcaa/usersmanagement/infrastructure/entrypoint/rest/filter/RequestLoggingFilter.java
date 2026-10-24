package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filtro que registra en el log cada petición HTTP entrante y su respuesta.
 *
 * <p>Se ejecuta antes que la cadena de Spring Security para que también se registren las peticiones
 * rechazadas (401/403). No registra el cuerpo de la petición para evitar exponer datos sensibles
 * (contraseñas, correos, etc.) en los logs, cumpliendo con la convención de no registrar PII.
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestLoggingFilter extends OncePerRequestFilter {

  private static final String SWAGGER_API_DOCS = "/v3/api-docs";
  private static final String SWAGGER_UI = "/swagger-ui";

  @Override
  protected void doFilterInternal(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final FilterChain filterChain)
      throws ServletException, IOException {

    final String method = request.getMethod();
    final String uri = request.getRequestURI();
    final long startTime = System.currentTimeMillis();

    log.info("--> {} {}", method, uri);

    try {
      filterChain.doFilter(request, response);
    } finally {
      final long duration = System.currentTimeMillis() - startTime;
      log.info("<-- {} {} {} ({}ms)", response.getStatus(), method, uri, duration);
    }
  }

  @Override
  protected boolean shouldNotFilter(final HttpServletRequest request) {
    final String path = request.getRequestURI();
    return path.startsWith(SWAGGER_API_DOCS) || path.startsWith(SWAGGER_UI);
  }
}
