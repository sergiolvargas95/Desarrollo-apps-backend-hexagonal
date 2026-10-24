package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetCursoByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetCursoByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetCursoByIdQuery;
import com.jcaa.usersmanagement.application.service.mapper.CursoApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.CursoNotFoundException;
import com.jcaa.usersmanagement.domain.model.CursoModel;
import com.jcaa.usersmanagement.domain.valueobject.CursoId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class GetCursoByIdService implements GetCursoByIdUseCase {

  private final GetCursoByIdPort getCursoByIdPort;
  private final Validator validator;

  @Override
  public CursoModel execute(final GetCursoByIdQuery query) {
    validateQuery(query);

    final CursoId cursoId = CursoApplicationMapper.fromGetCursoByIdQueryToCursoId(query);
    return getCursoByIdPort
        .getById(cursoId)
        .orElseThrow(() -> CursoNotFoundException.becauseIdWasNotFound(cursoId.value()));
  }

  private void validateQuery(final GetCursoByIdQuery query) {
    final Set<ConstraintViolation<GetCursoByIdQuery>> violations = validator.validate(query);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
