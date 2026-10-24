package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.SearchUsersUseCase;
import com.jcaa.usersmanagement.application.port.out.SearchUsersPort;
import com.jcaa.usersmanagement.application.service.dto.query.SearchUsersQuery;
import com.jcaa.usersmanagement.domain.model.UserModel;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchUsersService implements SearchUsersUseCase {

  private final SearchUsersPort searchUsersPort;

  @Override
  public List<UserModel> execute(final SearchUsersQuery query) {
    final Optional<String> nombre =
        isBlank(query.nombre()) ? Optional.empty() : Optional.of(query.nombre().trim());

    final Optional<String> email =
        isBlank(query.email()) ? Optional.empty() : Optional.of(query.email().trim());

    return searchUsersPort.search(nombre, email);
  }

  private static boolean isBlank(final String value) {
    return value == null || value.isBlank();
  }
}
