package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.CursoModel;

public interface UpdateCursoPort {
  CursoModel update(CursoModel curso);
}
