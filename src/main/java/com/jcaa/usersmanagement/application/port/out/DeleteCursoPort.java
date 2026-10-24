package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.valueobject.CursoId;

public interface DeleteCursoPort {
  void delete(CursoId cursoId);
}
