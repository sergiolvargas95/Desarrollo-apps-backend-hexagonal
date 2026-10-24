package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.enums.CursoModalidad;
import com.jcaa.usersmanagement.domain.enums.CursoNivel;
import com.jcaa.usersmanagement.domain.model.CursoModel;
import com.jcaa.usersmanagement.domain.valueobject.CursoCodigo;
import com.jcaa.usersmanagement.domain.valueobject.CursoDescripcion;
import com.jcaa.usersmanagement.domain.valueobject.CursoId;
import com.jcaa.usersmanagement.domain.valueobject.CursoInstitucion;
import com.jcaa.usersmanagement.domain.valueobject.CursoNombre;
import com.jcaa.usersmanagement.domain.valueobject.CursoUbicacion;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.CursoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.CursoEntity;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CursoPersistenceMapper {

  public CursoPersistenceDto fromModelToDto(final CursoModel curso) {
    return new CursoPersistenceDto(
        curso.getId().value(),
        curso.getNombre().value(),
        curso.getCodigo().value(),
        curso.getDescripcion().value(),
        curso.getInstitucion().value(),
        curso.getUbicacion().value(),
        curso.getModalidad().name(),
        curso.getNivel().name(),
        null,
        null);
  }

  public CursoEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
    return new CursoEntity(
        resultSet.getString("id"),
        resultSet.getString("nombre"),
        resultSet.getString("codigo"),
        resultSet.getString("descripcion"),
        resultSet.getString("institucion"),
        resultSet.getString("ubicacion"),
        resultSet.getString("modalidad"),
        resultSet.getString("nivel"),
        resultSet.getString("created_at"),
        resultSet.getString("updated_at"));
  }

  public CursoModel fromEntityToModel(final CursoEntity entity) {
    return new CursoModel(
        new CursoId(entity.id()),
        new CursoNombre(entity.nombre()),
        new CursoCodigo(entity.codigo()),
        new CursoDescripcion(entity.descripcion()),
        new CursoInstitucion(entity.institucion()),
        new CursoUbicacion(entity.ubicacion()),
        CursoModalidad.fromString(entity.modalidad()),
        CursoNivel.fromString(entity.nivel()));
  }

  public CursoModel fromResultSetToModel(final ResultSet resultSet) throws SQLException {
    return fromEntityToModel(fromResultSetToEntity(resultSet));
  }

  public List<CursoModel> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
    final List<CursoModel> cursos = new ArrayList<>();
    while (resultSet.next()) {
      cursos.add(fromResultSetToModel(resultSet));
    }
    return cursos;
  }
}
