package com.jcaa.usersmanagement.domain.exception;

public final class InvalidCursoNivelException extends DomainException {

  private static final String MESSAGE_INVALID =
      "The course level '%s' is invalid. Allowed values: BASICO, INTERMEDIO, AVANZADO.";

  private InvalidCursoNivelException(final String message) {
    super(message);
  }

  public static InvalidCursoNivelException becauseValueIsInvalid(final String value) {
    return new InvalidCursoNivelException(String.format(MESSAGE_INVALID, value));
  }
}
