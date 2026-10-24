package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetCursoByIdQuery;
import com.jcaa.usersmanagement.domain.model.CursoModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetCursoByIdUseCase {
  CursoModel execute(@NotNull @Valid GetCursoByIdQuery query);
}
