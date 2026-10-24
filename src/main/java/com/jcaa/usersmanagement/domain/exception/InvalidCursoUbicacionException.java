package com.jcaa.usersmanagement.domain.exception;

public final class InvalidCursoUbicacionException extends DomainException {

  private static final String MESSAGE_EMPTY = "The course location must not be empty.";
  private static final String MESSAGE_TOO_SHORT =
      "The course location must have at least %d characters.";

  private InvalidCursoUbicacionException(final String message) {
    super(message);
  }

  public static InvalidCursoUbicacionException becauseValueIsEmpty() {
    return new InvalidCursoUbicacionException(MESSAGE_EMPTY);
  }

  public static InvalidCursoUbicacionException becauseLengthIsTooShort(final int minimumLength) {
    return new InvalidCursoUbicacionException(String.format(MESSAGE_TOO_SHORT, minimumLength));
  }
}
