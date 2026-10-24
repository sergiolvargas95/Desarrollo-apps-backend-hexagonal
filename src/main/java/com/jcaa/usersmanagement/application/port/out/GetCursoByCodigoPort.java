package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.CursoModel;
import com.jcaa.usersmanagement.domain.valueobject.CursoCodigo;
import java.util.Optional;

public interface GetCursoByCodigoPort {
  Optional<CursoModel> getByCodigo(CursoCodigo codigo);
}
