package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.SearchCursosUseCase;
import com.jcaa.usersmanagement.application.port.out.SearchCursosPort;
import com.jcaa.usersmanagement.application.service.dto.query.SearchCursosQuery;
import com.jcaa.usersmanagement.domain.enums.CursoModalidad;
import com.jcaa.usersmanagement.domain.enums.CursoNivel;
import com.jcaa.usersmanagement.domain.model.CursoModel;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchCursosService implements SearchCursosUseCase {

  private final SearchCursosPort searchCursosPort;

  @Override
  public List<CursoModel> execute(final SearchCursosQuery query) {
    // Parsear cada parámetro: null/blank → Optional.empty(), valor → Optional.of(enum)
    // Los enums ya lanzan InvalidCursoNivelException / InvalidCursoModalidadException
    // si el valor recibido no coincide con ningún elemento — el GlobalExceptionHandler
    // los captura como 400 automáticamente.
    final Optional<CursoNivel> nivel =
        isBlank(query.nivel())
            ? Optional.empty()
            : Optional.of(CursoNivel.fromString(query.nivel()));

    final Optional<CursoModalidad> modalidad =
        isBlank(query.modalidad())
            ? Optional.empty()
            : Optional.of(CursoModalidad.fromString(query.modalidad()));

    return searchCursosPort.search(nivel, modalidad);
  }

  private static boolean isBlank(final String value) {
    return value == null || value.isBlank();
  }
}
