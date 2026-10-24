package com.jcaa.usersmanagement.domain.enums;

import com.jcaa.usersmanagement.domain.exception.InvalidCursoNivelException;

public enum CursoNivel {
  BASICO,
  INTERMEDIO,
  AVANZADO;

  public static CursoNivel fromString(final String value) {
    for (final CursoNivel nivel : values()) {
      if (nivel.name().equalsIgnoreCase(value)) {
        return nivel;
      }
    }
    throw InvalidCursoNivelException.becauseValueIsInvalid(value);
  }
}
