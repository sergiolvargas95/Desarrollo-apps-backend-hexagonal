package com.jcaa.usersmanagement.domain.exception;

public final class InvalidCursoModalidadException extends DomainException {

  private static final String MESSAGE_INVALID =
      "The course modality '%s' is invalid. Allowed values: VIRTUAL, PRESENCIAL, SEMIPRESENCIAL.";

  private InvalidCursoModalidadException(final String message) {
    super(message);
  }

  public static InvalidCursoModalidadException becauseValueIsInvalid(final String value) {
    return new InvalidCursoModalidadException(String.format(MESSAGE_INVALID, value));
  }
}
