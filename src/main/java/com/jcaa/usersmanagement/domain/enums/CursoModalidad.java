package com.jcaa.usersmanagement.domain.enums;

import com.jcaa.usersmanagement.domain.exception.InvalidCursoModalidadException;

public enum CursoModalidad {
  VIRTUAL,
  PRESENCIAL,
  SEMIPRESENCIAL;

  public static CursoModalidad fromString(final String value) {
    for (final CursoModalidad modalidad : values()) {
      if (modalidad.name().equalsIgnoreCase(value)) {
        return modalidad;
      }
    }
    throw InvalidCursoModalidadException.becauseValueIsInvalid(value);
  }
}
