package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.enums.CursoModalidad;
import com.jcaa.usersmanagement.domain.enums.CursoNivel;
import com.jcaa.usersmanagement.domain.valueobject.CursoCodigo;
import com.jcaa.usersmanagement.domain.valueobject.CursoDescripcion;
import com.jcaa.usersmanagement.domain.valueobject.CursoId;
import com.jcaa.usersmanagement.domain.valueobject.CursoInstitucion;
import com.jcaa.usersmanagement.domain.valueobject.CursoNombre;
import com.jcaa.usersmanagement.domain.valueobject.CursoUbicacion;
import lombok.Value;

@Value
public class CursoModel {

  CursoId id;
  CursoNombre nombre;
  CursoCodigo codigo;
  CursoDescripcion descripcion;
  CursoInstitucion institucion;
  CursoUbicacion ubicacion;
  CursoModalidad modalidad;
  CursoNivel nivel;

  public static CursoModel create(
      final CursoId id,
      final CursoNombre nombre,
      final CursoCodigo codigo,
      final CursoDescripcion descripcion,
      final CursoInstitucion institucion,
      final CursoUbicacion ubicacion,
      final CursoModalidad modalidad,
      final CursoNivel nivel) {
    return new CursoModel(id, nombre, codigo, descripcion, institucion, ubicacion, modalidad, nivel);
  }
}
