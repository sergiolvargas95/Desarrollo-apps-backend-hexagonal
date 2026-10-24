package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.enums.CursoModalidad;
import com.jcaa.usersmanagement.domain.enums.CursoNivel;
import com.jcaa.usersmanagement.domain.model.CursoModel;
import java.util.List;
import java.util.Optional;

public interface SearchCursosPort {

  /**
   * Busca cursos aplicando los filtros provistos.
   * Cualquier parámetro en {@link Optional#empty()} se ignora en la consulta.
   */
  List<CursoModel> search(Optional<CursoNivel> nivel, Optional<CursoModalidad> modalidad);
}
