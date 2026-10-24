package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.CursoModel;
import com.jcaa.usersmanagement.domain.valueobject.CursoId;
import java.util.Optional;

public interface GetCursoByIdPort {
  Optional<CursoModel> getById(CursoId cursoId);
}
