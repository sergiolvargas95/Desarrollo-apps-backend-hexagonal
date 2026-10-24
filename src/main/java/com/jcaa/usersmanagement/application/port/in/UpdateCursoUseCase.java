package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateCursoCommand;
import com.jcaa.usersmanagement.domain.model.CursoModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateCursoUseCase {
  CursoModel execute(@NotNull @Valid UpdateCursoCommand command);
}
