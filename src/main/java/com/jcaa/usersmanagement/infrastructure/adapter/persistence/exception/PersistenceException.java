package com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception;

public final class PersistenceException extends RuntimeException {

  private static final String MESSAGE_SAVE = "Failed to save user with ID: '%s'.";
  private static final String MESSAGE_UPDATE = "Failed to update user with ID: '%s'.";
  private static final String MESSAGE_FIND = "Failed to find user with ID: '%s'.";
  private static final String MESSAGE_EMAIL = "Failed to find user with email: '%s'.";
  private static final String MESSAGE_ALL = "Failed to retrieve all users.";
  private static final String MESSAGE_DELETE = "Failed to delete user with ID: '%s'.";
  private static final String MESSAGE_CONNECTION = "Could not establish database connection.";

  private static final String MESSAGE_CURSO_SAVE = "Failed to save course with ID: '%s'.";
  private static final String MESSAGE_CURSO_UPDATE = "Failed to update course with ID: '%s'.";
  private static final String MESSAGE_CURSO_FIND = "Failed to find course with ID: '%s'.";
  private static final String MESSAGE_CURSO_CODIGO = "Failed to find course with code: '%s'.";
  private static final String MESSAGE_CURSO_ALL = "Failed to retrieve all courses.";
  private static final String MESSAGE_CURSO_DELETE = "Failed to delete course with ID: '%s'.";
  private static final String MESSAGE_CURSO_INSTITUCION = "Failed to find courses by institution: '%s'.";

  private PersistenceException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public static PersistenceException becauseSaveFailed(final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_SAVE, userId), cause);
  }

  public static PersistenceException becauseUpdateFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_UPDATE, userId), cause);
  }

  public static PersistenceException becauseFindByIdFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_FIND, userId), cause);
  }

  public static PersistenceException becauseFindByEmailFailed(
      final String email, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_EMAIL, email), cause);
  }

  public static PersistenceException becauseFindAllFailed(final Throwable cause) {
    return new PersistenceException(MESSAGE_ALL, cause);
  }

  public static PersistenceException becauseDeleteFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_DELETE, userId), cause);
  }

  public static PersistenceException becauseConnectionFailed(final Throwable cause) {
    return new PersistenceException(MESSAGE_CONNECTION, cause);
  }

  public static PersistenceException becauseCursoSaveFailed(
      final String cursoId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_CURSO_SAVE, cursoId), cause);
  }

  public static PersistenceException becauseCursoUpdateFailed(
      final String cursoId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_CURSO_UPDATE, cursoId), cause);
  }

  public static PersistenceException becauseCursoFindByIdFailed(
      final String cursoId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_CURSO_FIND, cursoId), cause);
  }

  public static PersistenceException becauseCursoFindByCodigoFailed(
      final String codigo, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_CURSO_CODIGO, codigo), cause);
  }

  public static PersistenceException becauseCursoFindAllFailed(final Throwable cause) {
    return new PersistenceException(MESSAGE_CURSO_ALL, cause);
  }

  public static PersistenceException becauseCursoDeleteFailed(
      final String cursoId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_CURSO_DELETE, cursoId), cause);
  }

  public static PersistenceException becauseCursoFindByInstitucionFailed(
      final String institucion, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_CURSO_INSTITUCION, institucion), cause);
  }
}
