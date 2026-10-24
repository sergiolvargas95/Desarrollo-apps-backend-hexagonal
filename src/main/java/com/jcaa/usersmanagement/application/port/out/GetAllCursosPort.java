package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.CursoModel;
import java.util.List;

public interface GetAllCursosPort {
  List<CursoModel> getAll();
}
