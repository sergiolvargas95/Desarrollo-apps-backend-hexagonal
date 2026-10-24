package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateCursoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteCursoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateCursoCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetCursoByIdQuery;
import com.jcaa.usersmanagement.application.service.dto.query.GetCursosByInstitucionQuery;
import com.jcaa.usersmanagement.domain.enums.CursoModalidad;
import com.jcaa.usersmanagement.domain.enums.CursoNivel;
import com.jcaa.usersmanagement.domain.model.CursoModel;
import com.jcaa.usersmanagement.domain.valueobject.CursoCodigo;
import com.jcaa.usersmanagement.domain.valueobject.CursoDescripcion;
import com.jcaa.usersmanagement.domain.valueobject.CursoId;
import com.jcaa.usersmanagement.domain.valueobject.CursoInstitucion;
import com.jcaa.usersmanagement.domain.valueobject.CursoNombre;
import com.jcaa.usersmanagement.domain.valueobject.CursoUbicacion;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CursoApplicationMapper {

  public CursoModel fromCreateCommandToModel(final CreateCursoCommand command) {
    return CursoModel.create(
        new CursoId(command.id()),
        new CursoNombre(command.nombre()),
        new CursoCodigo(command.codigo()),
        new CursoDescripcion(command.descripcion()),
        new CursoInstitucion(command.institucion()),
        new CursoUbicacion(command.ubicacion()),
        CursoModalidad.fromString(command.modalidad()),
        CursoNivel.fromString(command.nivel()));
  }

  public CursoModel fromUpdateCommandToModel(final UpdateCursoCommand command) {
    return new CursoModel(
        new CursoId(command.id()),
        new CursoNombre(command.nombre()),
        new CursoCodigo(command.codigo()),
        new CursoDescripcion(command.descripcion()),
        new CursoInstitucion(command.institucion()),
        new CursoUbicacion(command.ubicacion()),
        CursoModalidad.fromString(command.modalidad()),
        CursoNivel.fromString(command.nivel()));
  }

  public CursoId fromGetCursoByIdQueryToCursoId(final GetCursoByIdQuery query) {
    return new CursoId(query.id());
  }

  public CursoInstitucion fromGetCursosByInstitucionQueryToCursoInstitucion(
      final GetCursosByInstitucionQuery query) {
    return new CursoInstitucion(query.institucion());
  }

  public CursoId fromDeleteCommandToCursoId(final DeleteCursoCommand command) {
    return new CursoId(command.id());
  }
}
