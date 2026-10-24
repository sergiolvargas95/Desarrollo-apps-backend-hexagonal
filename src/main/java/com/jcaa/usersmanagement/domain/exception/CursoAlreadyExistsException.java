package com.jcaa.usersmanagement.domain.exception;

public final class CursoAlreadyExistsException extends DomainException {

  private static final String MESSAGE_CODIGO_EXISTS = "A course with code '%s' already exists.";

  private CursoAlreadyExistsException(final String message) {
    super(message);
  }

  public static CursoAlreadyExistsException becauseCodigoAlreadyExists(final String codigo) {
    return new CursoAlreadyExistsException(String.format(MESSAGE_CODIGO_EXISTS, codigo));
  }
}
