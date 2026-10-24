package com.jcaa.usersmanagement.domain.exception;

public final class CursoNotFoundException extends DomainException {

  private static final String MESSAGE_BY_ID = "The course with id '%s' was not found.";

  private CursoNotFoundException(final String message) {
    super(message);
  }

  public static CursoNotFoundException becauseIdWasNotFound(final String cursoId) {
    return new CursoNotFoundException(String.format(MESSAGE_BY_ID, cursoId));
  }
}
