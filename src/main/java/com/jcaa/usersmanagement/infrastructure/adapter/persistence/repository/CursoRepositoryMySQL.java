package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteCursoPort;
import com.jcaa.usersmanagement.application.port.out.GetAllCursosPort;
import com.jcaa.usersmanagement.application.port.out.GetCursoByCodigoPort;
import com.jcaa.usersmanagement.application.port.out.GetCursoByIdPort;
import com.jcaa.usersmanagement.application.port.out.GetCursosByInstitucionPort;
import com.jcaa.usersmanagement.application.port.out.SaveCursoPort;
import com.jcaa.usersmanagement.application.port.out.SearchCursosPort;
import com.jcaa.usersmanagement.application.port.out.UpdateCursoPort;
import com.jcaa.usersmanagement.domain.enums.CursoModalidad;
import com.jcaa.usersmanagement.domain.enums.CursoNivel;
import com.jcaa.usersmanagement.domain.exception.CursoNotFoundException;
import com.jcaa.usersmanagement.domain.model.CursoModel;
import com.jcaa.usersmanagement.domain.valueobject.CursoCodigo;
import com.jcaa.usersmanagement.domain.valueobject.CursoId;
import com.jcaa.usersmanagement.domain.valueobject.CursoInstitucion;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.CursoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.CursoPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CursoRepositoryMySQL
    implements SaveCursoPort,
        UpdateCursoPort,
        GetCursoByIdPort,
        GetCursoByCodigoPort,
        GetAllCursosPort,
        DeleteCursoPort,
        GetCursosByInstitucionPort,
        SearchCursosPort {

  private static final String COLUMNS =
      "id, nombre, codigo, descripcion, institucion, ubicacion, modalidad, nivel, created_at, updated_at";

  private static final String SQL_INSERT =
      "INSERT INTO cursos (id, nombre, codigo, descripcion, institucion, ubicacion, modalidad, nivel, created_at, updated_at) "
          + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

  private static final String SQL_UPDATE =
      "UPDATE cursos SET nombre = ?, codigo = ?, descripcion = ?, institucion = ?, ubicacion = ?, "
          + "modalidad = ?, nivel = ?, updated_at = NOW() WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
      "SELECT " + COLUMNS + " FROM cursos WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_BY_CODIGO =
      "SELECT " + COLUMNS + " FROM cursos WHERE codigo = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
      "SELECT " + COLUMNS + " FROM cursos ORDER BY nombre ASC";

  private static final String SQL_SELECT_BY_INSTITUCION =
      "SELECT " + COLUMNS + " FROM cursos WHERE institucion = ? ORDER BY nombre ASC";

  private static final String SQL_SEARCH_BASE =
      "SELECT " + COLUMNS + " FROM cursos";

  private static final String SQL_DELETE = "DELETE FROM cursos WHERE id = ?";

  private final DataSource dataSource;

  // ─── save ──────────────────────────────────────────────────────────────────

  @Override
  public CursoModel save(final CursoModel curso) {
    final CursoPersistenceDto dto = CursoPersistenceMapper.fromModelToDto(curso);
    executeSave(dto);
    return findByIdOrFail(curso.getId());
  }

  // ─── update ────────────────────────────────────────────────────────────────

  @Override
  public CursoModel update(final CursoModel curso) {
    final CursoPersistenceDto dto = CursoPersistenceMapper.fromModelToDto(curso);
    executeUpdate(dto);
    return findByIdOrFail(curso.getId());
  }

  // ─── getById ───────────────────────────────────────────────────────────────

  @Override
  public Optional<CursoModel> getById(final CursoId cursoId) {
    try (final Connection connection = dataSource.getConnection();
        final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
      statement.setString(1, cursoId.value());
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(CursoPersistenceMapper.fromResultSetToModel(resultSet));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseCursoFindByIdFailed(cursoId.value(), exception);
    }
  }

  // ─── getByCodigo ───────────────────────────────────────────────────────────

  @Override
  public Optional<CursoModel> getByCodigo(final CursoCodigo codigo) {
    try (final Connection connection = dataSource.getConnection();
        final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_CODIGO)) {
      statement.setString(1, codigo.value());
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(CursoPersistenceMapper.fromResultSetToModel(resultSet));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseCursoFindByCodigoFailed(codigo.value(), exception);
    }
  }

  // ─── getAll ────────────────────────────────────────────────────────────────

  @Override
  public List<CursoModel> getAll() {
    try (final Connection connection = dataSource.getConnection();
        final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet resultSet = statement.executeQuery();
      return CursoPersistenceMapper.fromResultSetToModelList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseCursoFindAllFailed(exception);
    }
  }

  // ─── search (nivel y/o modalidad opcionales) ──────────────────────────────

  @Override
  public List<CursoModel> search(
      final Optional<CursoNivel> nivel, final Optional<CursoModalidad> modalidad) {

    // Construye WHERE dinámico según los filtros presentes
    final List<String> conditions = new ArrayList<>();
    if (nivel.isPresent()) {
      conditions.add("nivel = ?");
    }
    if (modalidad.isPresent()) {
      conditions.add("modalidad = ?");
    }

    final String sql =
        conditions.isEmpty()
            ? SQL_SEARCH_BASE + " ORDER BY nombre ASC"
            : SQL_SEARCH_BASE
                + " WHERE "
                + String.join(" AND ", conditions)
                + " ORDER BY nombre ASC";

    try (final Connection connection = dataSource.getConnection();
        final PreparedStatement statement = connection.prepareStatement(sql)) {

      // Asigna parámetros en el mismo orden que se añadieron las condiciones
      int paramIndex = 1;
      if (nivel.isPresent()) {
        statement.setString(paramIndex++, nivel.get().name());
      }
      if (modalidad.isPresent()) {
        statement.setString(paramIndex, modalidad.get().name());
      }

      final ResultSet resultSet = statement.executeQuery();
      return CursoPersistenceMapper.fromResultSetToModelList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseCursoFindAllFailed(exception);
    }
  }

  // ─── getByInstitucion ──────────────────────────────────────────────────────

  @Override
  public List<CursoModel> getByInstitucion(final CursoInstitucion institucion) {
    try (final Connection connection = dataSource.getConnection();
        final PreparedStatement statement =
            connection.prepareStatement(SQL_SELECT_BY_INSTITUCION)) {
      statement.setString(1, institucion.value());
      final ResultSet resultSet = statement.executeQuery();
      return CursoPersistenceMapper.fromResultSetToModelList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseCursoFindByInstitucionFailed(
          institucion.value(), exception);
    }
  }

  // ─── delete ────────────────────────────────────────────────────────────────

  @Override
  public void delete(final CursoId cursoId) {
    try (final Connection connection = dataSource.getConnection();
        final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
      statement.setString(1, cursoId.value());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseCursoDeleteFailed(cursoId.value(), exception);
    }
  }

  // ─── helpers ───────────────────────────────────────────────────────────────

  private void executeSave(final CursoPersistenceDto dto) {
    try (final Connection connection = dataSource.getConnection();
        final PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
      statement.setString(1, dto.id());
      statement.setString(2, dto.nombre());
      statement.setString(3, dto.codigo());
      statement.setString(4, dto.descripcion());
      statement.setString(5, dto.institucion());
      statement.setString(6, dto.ubicacion());
      statement.setString(7, dto.modalidad());
      statement.setString(8, dto.nivel());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseCursoSaveFailed(dto.id(), exception);
    }
  }

  private void executeUpdate(final CursoPersistenceDto dto) {
    try (final Connection connection = dataSource.getConnection();
        final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
      statement.setString(1, dto.nombre());
      statement.setString(2, dto.codigo());
      statement.setString(3, dto.descripcion());
      statement.setString(4, dto.institucion());
      statement.setString(5, dto.ubicacion());
      statement.setString(6, dto.modalidad());
      statement.setString(7, dto.nivel());
      statement.setString(8, dto.id());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseCursoUpdateFailed(dto.id(), exception);
    }
  }

  private CursoModel findByIdOrFail(final CursoId cursoId) {
    return getById(cursoId)
        .orElseThrow(() -> CursoNotFoundException.becauseIdWasNotFound(cursoId.value()));
  }
}
