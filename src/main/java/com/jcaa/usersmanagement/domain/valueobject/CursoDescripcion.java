package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidCursoDescripcionException;
import java.util.Objects;

public record CursoDescripcion(String value) {

  public CursoDescripcion {
    final String normalizedValue = Objects.requireNonNull(value, "CursoDescripcion cannot be null").trim();
    validateNotEmpty(normalizedValue);
    value = normalizedValue;
  }

  private static void validateNotEmpty(final String normalizedValue) {
    if (normalizedValue.isEmpty()) {
      throw InvalidCursoDescripcionException.becauseValueIsEmpty();
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
