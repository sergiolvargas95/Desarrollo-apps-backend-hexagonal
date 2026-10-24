package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateCursoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteCursoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateCursoCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetCursoByIdQuery;
import com.jcaa.usersmanagement.application.service.dto.query.GetCursosByInstitucionQuery;
import com.jcaa.usersmanagement.application.service.dto.query.SearchCursosQuery;
import com.jcaa.usersmanagement.domain.model.CursoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateCursoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdateCursoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.CursoRestResponse;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class CursoRestMapper {

  public CreateCursoCommand toCreateCommand(final CreateCursoRestRequest request) {
    return new CreateCursoCommand(
        request.id(),
        request.nombre(),
        request.codigo(),
        request.descripcion(),
        request.institucion(),
        request.ubicacion(),
        request.modalidad(),
        request.nivel());
  }

  public UpdateCursoCommand toUpdateCommand(final String id, final UpdateCursoRestRequest request) {
    return new UpdateCursoCommand(
        id,
        request.nombre(),
        request.codigo(),
        request.descripcion(),
        request.institucion(),
        request.ubicacion(),
        request.modalidad(),
        request.nivel());
  }

  public GetCursoByIdQuery toGetByIdQuery(final String id) {
    return new GetCursoByIdQuery(id);
  }

  public GetCursosByInstitucionQuery toGetByInstitucionQuery(final String institucion) {
    return new GetCursosByInstitucionQuery(institucion);
  }

  public SearchCursosQuery toSearchQuery(final String nivel, final String modalidad) {
    return new SearchCursosQuery(nivel, modalidad);
  }

  public DeleteCursoCommand toDeleteCommand(final String id) {
    return new DeleteCursoCommand(id);
  }

  public CursoRestResponse toResponse(final CursoModel curso) {
    return new CursoRestResponse(
        curso.getId().value(),
        curso.getNombre().value(),
        curso.getCodigo().value(),
        curso.getDescripcion().value(),
        curso.getInstitucion().value(),
        curso.getUbicacion().value(),
        curso.getModalidad().name(),
        curso.getNivel().name());
  }

  public List<CursoRestResponse> toResponseList(final List<CursoModel> cursos) {
    return cursos.stream().map(CursoRestMapper::toResponse).toList();
  }
}
