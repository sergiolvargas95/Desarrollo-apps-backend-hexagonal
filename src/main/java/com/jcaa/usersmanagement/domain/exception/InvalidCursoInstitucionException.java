package com.jcaa.usersmanagement.domain.exception;

public final class InvalidCursoInstitucionException extends DomainException {

  private static final String MESSAGE_EMPTY = "The institution name must not be empty.";
  private static final String MESSAGE_TOO_SHORT =
      "The institution name must have at least %d characters.";

  private InvalidCursoInstitucionException(final String message) {
    super(message);
  }

  public static InvalidCursoInstitucionException becauseValueIsEmpty() {
    return new InvalidCursoInstitucionException(MESSAGE_EMPTY);
  }

  public static InvalidCursoInstitucionException becauseLengthIsTooShort(final int minimumLength) {
    return new InvalidCursoInstitucionException(String.format(MESSAGE_TOO_SHORT, minimumLength));
  }
}
