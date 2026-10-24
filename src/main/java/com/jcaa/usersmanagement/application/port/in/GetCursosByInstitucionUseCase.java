package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetCursosByInstitucionQuery;
import com.jcaa.usersmanagement.domain.model.CursoModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public interface GetCursosByInstitucionUseCase {
  List<CursoModel> execute(@NotNull @Valid GetCursosByInstitucionQuery query);
}
