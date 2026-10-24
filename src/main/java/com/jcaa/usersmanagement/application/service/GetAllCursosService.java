package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllCursosUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllCursosPort;
import com.jcaa.usersmanagement.domain.model.CursoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllCursosService implements GetAllCursosUseCase {

  private final GetAllCursosPort getAllCursosPort;

  @Override
  public List<CursoModel> execute() {
    return getAllCursosPort.getAll();
  }
}
