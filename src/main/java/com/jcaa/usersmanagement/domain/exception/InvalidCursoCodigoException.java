package com.jcaa.usersmanagement.domain.exception;

public final class InvalidCursoCodigoException extends DomainException {

  private static final String MESSAGE_EMPTY = "The course code must not be empty.";
  private static final String MESSAGE_TOO_SHORT = "The course code must have at least %d characters.";

  private InvalidCursoCodigoException(final String message) {
    super(message);
  }

  public static InvalidCursoCodigoException becauseValueIsEmpty() {
    return new InvalidCursoCodigoException(MESSAGE_EMPTY);
  }

  public static InvalidCursoCodigoException becauseLengthIsTooShort(final int minimumLength) {
    return new InvalidCursoCodigoException(String.format(MESSAGE_TOO_SHORT, minimumLength));
  }
}
