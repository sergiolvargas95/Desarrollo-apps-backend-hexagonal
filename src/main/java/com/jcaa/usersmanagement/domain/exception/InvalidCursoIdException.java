package com.jcaa.usersmanagement.domain.exception;

public final class InvalidCursoIdException extends DomainException {

  private static final String MESSAGE_EMPTY = "The course id must not be empty.";

  private InvalidCursoIdException(final String message) {
    super(message);
  }

  public static InvalidCursoIdException becauseValueIsEmpty() {
    return new InvalidCursoIdException(MESSAGE_EMPTY);
  }
}
