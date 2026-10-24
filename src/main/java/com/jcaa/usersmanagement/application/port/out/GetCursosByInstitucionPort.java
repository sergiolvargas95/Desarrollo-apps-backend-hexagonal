package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.CursoModel;
import com.jcaa.usersmanagement.domain.valueobject.CursoInstitucion;
import java.util.List;

public interface GetCursosByInstitucionPort {
  List<CursoModel> getByInstitucion(CursoInstitucion institucion);
}
