package com.jcaa.usersmanagement.domain.exception;

public final class InvalidCursoNombreException extends DomainException {

  private static final String MESSAGE_EMPTY = "The course name must not be empty.";
  private static final String MESSAGE_TOO_SHORT = "The course name must have at least %d characters.";

  private InvalidCursoNombreException(final String message) {
    super(message);
  }

  public static InvalidCursoNombreException becauseValueIsEmpty() {
    return new InvalidCursoNombreException(MESSAGE_EMPTY);
  }

  public static InvalidCursoNombreException becauseLengthIsTooShort(final int minimumLength) {
    return new InvalidCursoNombreException(String.format(MESSAGE_TOO_SHORT, minimumLength));
  }
}
