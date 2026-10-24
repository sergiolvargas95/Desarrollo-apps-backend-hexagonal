package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateCursoCommand;
import com.jcaa.usersmanagement.domain.model.CursoModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateCursoUseCase {
  CursoModel execute(@NotNull @Valid CreateCursoCommand command);
}
