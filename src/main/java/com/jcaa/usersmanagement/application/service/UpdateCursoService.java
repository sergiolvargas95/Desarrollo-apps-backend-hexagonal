package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateCursoUseCase;
import com.jcaa.usersmanagement.application.port.out.GetCursoByCodigoPort;
import com.jcaa.usersmanagement.application.port.out.GetCursoByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateCursoPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateCursoCommand;
import com.jcaa.usersmanagement.application.service.mapper.CursoApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.CursoAlreadyExistsException;
import com.jcaa.usersmanagement.domain.exception.CursoNotFoundException;
import com.jcaa.usersmanagement.domain.model.CursoModel;
import com.jcaa.usersmanagement.domain.valueobject.CursoCodigo;
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
public class UpdateCursoService implements UpdateCursoUseCase {

  private final UpdateCursoPort updateCursoPort;
  private final GetCursoByIdPort getCursoByIdPort;
  private final GetCursoByCodigoPort getCursoByCodigoPort;
  private final Validator validator;

  @Override
  public CursoModel execute(final UpdateCursoCommand command) {
    validateCommand(command);

    final CursoId cursoId = new CursoId(command.id());
    findExistingCursoOrFail(cursoId);

    final CursoCodigo newCodigo = new CursoCodigo(command.codigo());
    ensureCodigoIsNotTakenByAnotherCurso(newCodigo, cursoId);

    final CursoModel cursoToUpdate = CursoApplicationMapper.fromUpdateCommandToModel(command);
    return updateCursoPort.update(cursoToUpdate);
  }

  private void validateCommand(final UpdateCursoCommand command) {
    final Set<ConstraintViolation<UpdateCursoCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private CursoModel findExistingCursoOrFail(final CursoId cursoId) {
    return getCursoByIdPort
        .getById(cursoId)
        .orElseThrow(() -> CursoNotFoundException.becauseIdWasNotFound(cursoId.value()));
  }

  private void ensureCodigoIsNotTakenByAnotherCurso(final CursoCodigo newCodigo, final CursoId ownerId) {
    getCursoByCodigoPort
        .getByCodigo(newCodigo)
        .ifPresent(
            found -> {
              if (!found.getId().equals(ownerId)) {
                throw CursoAlreadyExistsException.becauseCodigoAlreadyExists(newCodigo.value());
              }
            });
  }
}
