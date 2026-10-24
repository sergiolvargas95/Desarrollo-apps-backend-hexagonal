package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.UserModel;
import java.util.List;
import java.util.Optional;

public interface SearchUsersPort {

  /**
   * Busca usuarios aplicando filtros parciales (LIKE) sobre nombre y/o email.
   * Cualquier parámetro en {@link Optional#empty()} se ignora en la consulta.
   */
  List<UserModel> search(Optional<String> nombre, Optional<String> email);
}
