package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetCursosByInstitucionUseCase;
import com.jcaa.usersmanagement.application.port.out.GetCursosByInstitucionPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetCursosByInstitucionQuery;
import com.jcaa.usersmanagement.application.service.mapper.CursoApplicationMapper;
import com.jcaa.usersmanagement.domain.model.CursoModel;
import com.jcaa.usersmanagement.domain.valueobject.CursoInstitucion;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GetCursosByInstitucionService implements GetCursosByInstitucionUseCase {

  private final GetCursosByInstitucionPort getCursosByInstitucionPort;
  private final Validator validator;

  @Override
  public List<CursoModel> execute(final GetCursosByInstitucionQuery query) {
    validateQuery(query);

    final CursoInstitucion institucion =
        CursoApplicationMapper.fromGetCursosByInstitucionQueryToCursoInstitucion(query);
    return getCursosByInstitucionPort.getByInstitucion(institucion);
  }

  private void validateQuery(final GetCursosByInstitucionQuery query) {
    final Set<ConstraintViolation<GetCursosByInstitucionQuery>> violations = validator.validate(query);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
