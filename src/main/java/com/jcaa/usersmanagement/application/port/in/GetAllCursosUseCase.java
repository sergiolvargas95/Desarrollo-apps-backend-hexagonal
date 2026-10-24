package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.CursoModel;
import java.util.List;

public interface GetAllCursosUseCase {
  List<CursoModel> execute();
}
