package com.jcaa.usersmanagement.domain.exception;

public final class InvalidCursoDescripcionException extends DomainException {

  private static final String MESSAGE_EMPTY = "The course description must not be empty.";

  private InvalidCursoDescripcionException(final String message) {
    super(message);
  }

  public static InvalidCursoDescripcionException becauseValueIsEmpty() {
    return new InvalidCursoDescripcionException(MESSAGE_EMPTY);
  }
}
