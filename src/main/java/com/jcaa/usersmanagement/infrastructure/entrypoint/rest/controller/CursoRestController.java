package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.controller;

import com.jcaa.usersmanagement.application.port.in.CreateCursoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteCursoUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllCursosUseCase;
import com.jcaa.usersmanagement.application.port.in.GetCursoByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.GetCursosByInstitucionUseCase;
import com.jcaa.usersmanagement.application.port.in.SearchCursosUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateCursoUseCase;
import com.jcaa.usersmanagement.application.service.dto.command.CreateCursoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteCursoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateCursoCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetCursoByIdQuery;
import com.jcaa.usersmanagement.application.service.dto.query.GetCursosByInstitucionQuery;
import com.jcaa.usersmanagement.application.service.dto.query.SearchCursosQuery;
import com.jcaa.usersmanagement.domain.model.CursoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateCursoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdateCursoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.CursoRestResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.mapper.CursoRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoRestController implements CursoRestControllerDocs {

  private final CreateCursoUseCase createCursoUseCase;
  private final UpdateCursoUseCase updateCursoUseCase;
  private final DeleteCursoUseCase deleteCursoUseCase;
  private final GetCursoByIdUseCase getCursoByIdUseCase;
  private final GetAllCursosUseCase getAllCursosUseCase;
  private final GetCursosByInstitucionUseCase getCursosByInstitucionUseCase;
  private final SearchCursosUseCase searchCursosUseCase;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CursoRestResponse create(@Valid @RequestBody final CreateCursoRestRequest request) {
    final CreateCursoCommand command = CursoRestMapper.toCreateCommand(request);
    final CursoModel curso = createCursoUseCase.execute(command);
    return CursoRestMapper.toResponse(curso);
  }

  @GetMapping
  public List<CursoRestResponse> getAll() {
    return CursoRestMapper.toResponseList(getAllCursosUseCase.execute());
  }

  @GetMapping("/search")
  public List<CursoRestResponse> search(
      @RequestParam(required = false) final String nivel,
      @RequestParam(required = false) final String modalidad) {
    final SearchCursosQuery query = CursoRestMapper.toSearchQuery(nivel, modalidad);
    return CursoRestMapper.toResponseList(searchCursosUseCase.execute(query));
  }

  @GetMapping("/institucion/{institucion}")
  public List<CursoRestResponse> getByInstitucion(@PathVariable final String institucion) {
    final GetCursosByInstitucionQuery query = CursoRestMapper.toGetByInstitucionQuery(institucion);
    final List<CursoModel> cursos = getCursosByInstitucionUseCase.execute(query);
    return CursoRestMapper.toResponseList(cursos);
  }

  @GetMapping("/{id}")
  public CursoRestResponse getById(@PathVariable final String id) {
    final GetCursoByIdQuery query = CursoRestMapper.toGetByIdQuery(id);
    final CursoModel curso = getCursoByIdUseCase.execute(query);
    return CursoRestMapper.toResponse(curso);
  }

  @PutMapping("/{id}")
  public CursoRestResponse update(
      @PathVariable final String id,
      @Valid @RequestBody final UpdateCursoRestRequest request) {
    final UpdateCursoCommand command = CursoRestMapper.toUpdateCommand(id, request);
    final CursoModel curso = updateCursoUseCase.execute(command);
    return CursoRestMapper.toResponse(curso);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable final String id) {
    final DeleteCursoCommand command = CursoRestMapper.toDeleteCommand(id);
    deleteCursoUseCase.execute(command);
  }
}
