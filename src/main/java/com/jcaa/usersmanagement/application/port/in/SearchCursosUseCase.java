package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.SearchCursosQuery;
import com.jcaa.usersmanagement.domain.model.CursoModel;
import java.util.List;

public interface SearchCursosUseCase {
  List<CursoModel> execute(SearchCursosQuery query);
}
