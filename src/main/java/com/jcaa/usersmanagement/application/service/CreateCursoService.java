package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateCursoUseCase;
import com.jcaa.usersmanagement.application.port.out.GetCursoByCodigoPort;
import com.jcaa.usersmanagement.application.port.out.SaveCursoPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateCursoCommand;
import com.jcaa.usersmanagement.application.service.mapper.CursoApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.CursoAlreadyExistsException;
import com.jcaa.usersmanagement.domain.model.CursoModel;
import com.jcaa.usersmanagement.domain.valueobject.CursoCodigo;
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
public class CreateCursoService implements CreateCursoUseCase {

  private final SaveCursoPort saveCursoPort;
  private final GetCursoByCodigoPort getCursoByCodigoPort;
  private final Validator validator;

  @Override
  public CursoModel execute(final CreateCursoCommand command) {
    validateCommand(command);

    final CursoCodigo codigo = new CursoCodigo(command.codigo());
    ensureCodigoIsNotTaken(codigo);

    final CursoModel cursoToSave = CursoApplicationMapper.fromCreateCommandToModel(command);
    return saveCursoPort.save(cursoToSave);
  }

  private void validateCommand(final CreateCursoCommand command) {
    final Set<ConstraintViolation<CreateCursoCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void ensureCodigoIsNotTaken(final CursoCodigo codigo) {
    getCursoByCodigoPort
        .getByCodigo(codigo)
        .ifPresent(
            ignored -> {
              throw CursoAlreadyExistsException.becauseCodigoAlreadyExists(codigo.value());
            });
  }
}
