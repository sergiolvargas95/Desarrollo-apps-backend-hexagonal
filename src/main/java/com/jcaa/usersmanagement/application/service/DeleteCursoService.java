package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteCursoUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteCursoPort;
import com.jcaa.usersmanagement.application.port.out.GetCursoByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteCursoCommand;
import com.jcaa.usersmanagement.application.service.mapper.CursoApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.CursoNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.CursoId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteCursoService implements DeleteCursoUseCase {

  private final DeleteCursoPort deleteCursoPort;
  private final GetCursoByIdPort getCursoByIdPort;
  private final Validator validator;

  @Override
  public void execute(final DeleteCursoCommand command) {
    validateCommand(command);

    final CursoId cursoId = CursoApplicationMapper.fromDeleteCommandToCursoId(command);
    ensureCursoExists(cursoId);
    deleteCursoPort.delete(cursoId);
  }

  private void validateCommand(final DeleteCursoCommand command) {
    final Set<ConstraintViolation<DeleteCursoCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void ensureCursoExists(final CursoId cursoId) {
    getCursoByIdPort
        .getById(cursoId)
        .orElseThrow(() -> CursoNotFoundException.becauseIdWasNotFound(cursoId.value()));
  }
}
