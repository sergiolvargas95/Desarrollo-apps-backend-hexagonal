package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.controller;

import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateCursoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdateCursoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.ApiErrorResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.CursoRestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Contrato OpenAPI / Swagger para {@link CursoRestController}.
 *
 * <p>Toda la documentación de la API vive aquí para mantener el controlador limpio.
 */
@Tag(name = "Cursos", description = "Gestión de cursos: crear, consultar, actualizar y eliminar.")
public interface CursoRestControllerDocs {

  // ─────────────────────────────────────────────────────────────────────────────
  // POST /api/cursos
  // ─────────────────────────────────────────────────────────────────────────────

  @Operation(
      summary = "Crear curso",
      description =
          "Registra un nuevo curso. El ID debe ser único y el código no puede estar en uso. "
              + "Modalidad: VIRTUAL | PRESENCIAL | SEMIPRESENCIAL. "
              + "Nivel: BASICO | INTERMEDIO | AVANZADO.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "201",
        description = "Curso creado exitosamente.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = CursoRestResponse.class))),
    @ApiResponse(
        responseCode = "400",
        description =
            "Datos inválidos: campos requeridos vacíos, modalidad o nivel no reconocidos.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class))),
    @ApiResponse(
        responseCode = "409",
        description = "Ya existe un curso con ese código.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class))),
    @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class)))
  })
  CursoRestResponse create(@Valid @RequestBody CreateCursoRestRequest request);

  // ─────────────────────────────────────────────────────────────────────────────
  // GET /api/cursos
  // ─────────────────────────────────────────────────────────────────────────────

  @Operation(
      summary = "Listar todos los cursos",
      description = "Retorna la lista completa de cursos ordenada alfabéticamente por nombre.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Lista de cursos obtenida exitosamente. Puede ser vacía.",
        content =
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = CursoRestResponse.class)))),
    @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class)))
  })
  List<CursoRestResponse> getAll();

  // ─────────────────────────────────────────────────────────────────────────────
  // GET /api/cursos/search?nivel=&modalidad=
  // ─────────────────────────────────────────────────────────────────────────────

  @Operation(
      summary = "Buscar cursos por nivel y/o modalidad",
      description =
          "Filtra cursos por nivel, modalidad o ambos. Si no se envía ningún parámetro, "
              + "retorna todos los cursos ordenados por nombre. "
              + "Valores aceptados para nivel: BASICO, INTERMEDIO, AVANZADO. "
              + "Valores aceptados para modalidad: VIRTUAL, PRESENCIAL, SEMIPRESENCIAL.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Lista de cursos que coinciden con los filtros. Puede ser vacía.",
        content =
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = CursoRestResponse.class)))),
    @ApiResponse(
        responseCode = "400",
        description = "El valor de nivel o modalidad no es reconocido.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class))),
    @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class)))
  })
  List<CursoRestResponse> search(
      @Parameter(
              description = "Filtrar por nivel. Valores: BASICO, INTERMEDIO, AVANZADO.",
              example = "BASICO")
          @org.springframework.web.bind.annotation.RequestParam(required = false)
          String nivel,
      @Parameter(
              description = "Filtrar por modalidad. Valores: VIRTUAL, PRESENCIAL, SEMIPRESENCIAL.",
              example = "VIRTUAL")
          @org.springframework.web.bind.annotation.RequestParam(required = false)
          String modalidad);

  // ─────────────────────────────────────────────────────────────────────────────
  // GET /api/cursos/institucion/{institucion}
  // ─────────────────────────────────────────────────────────────────────────────

  @Operation(
      summary = "Buscar cursos por institución",
      description =
          "Retorna los cursos que pertenecen a la institución indicada, "
              + "ordenados alfabéticamente por nombre. "
              + "Si ningún curso coincide, retorna una lista vacía.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Lista de cursos de la institución. Puede ser vacía.",
        content =
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = CursoRestResponse.class)))),
    @ApiResponse(
        responseCode = "400",
        description = "El nombre de la institución es inválido (vacío o demasiado corto).",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class))),
    @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class)))
  })
  List<CursoRestResponse> getByInstitucion(
      @Parameter(
              description = "Nombre exacto de la institución a filtrar.",
              example = "UDC",
              required = true)
          @PathVariable
          String institucion);

  // ─────────────────────────────────────────────────────────────────────────────
  // GET /api/cursos/{id}
  // ─────────────────────────────────────────────────────────────────────────────

  @Operation(
      summary = "Obtener curso por ID",
      description = "Retorna los datos de un curso específico identificado por su ID único.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Curso encontrado.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = CursoRestResponse.class))),
    @ApiResponse(
        responseCode = "404",
        description = "No existe ningún curso con el ID proporcionado.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class))),
    @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class)))
  })
  CursoRestResponse getById(
      @Parameter(description = "ID único del curso.", example = "cur-001", required = true)
          @PathVariable
          String id);

  // ─────────────────────────────────────────────────────────────────────────────
  // PUT /api/cursos/{id}
  // ─────────────────────────────────────────────────────────────────────────────

  @Operation(
      summary = "Actualizar curso",
      description =
          "Actualiza los datos de un curso existente. "
              + "Si se cambia el código, no debe estar en uso por otro curso. "
              + "Modalidad: VIRTUAL | PRESENCIAL | SEMIPRESENCIAL. "
              + "Nivel: BASICO | INTERMEDIO | AVANZADO.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Curso actualizado exitosamente.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = CursoRestResponse.class))),
    @ApiResponse(
        responseCode = "400",
        description = "Datos inválidos: campos requeridos vacíos, modalidad o nivel no reconocidos.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class))),
    @ApiResponse(
        responseCode = "404",
        description = "No existe ningún curso con el ID proporcionado.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class))),
    @ApiResponse(
        responseCode = "409",
        description = "El nuevo código ya está en uso por otro curso.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class))),
    @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class)))
  })
  CursoRestResponse update(
      @Parameter(
              description = "ID único del curso a actualizar.",
              example = "cur-001",
              required = true)
          @PathVariable
          String id,
      @Valid @RequestBody UpdateCursoRestRequest request);

  // ─────────────────────────────────────────────────────────────────────────────
  // DELETE /api/cursos/{id}
  // ─────────────────────────────────────────────────────────────────────────────

  @Operation(
      summary = "Eliminar curso",
      description = "Elimina permanentemente un curso del sistema por su ID.")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = "Curso eliminado exitosamente."),
    @ApiResponse(
        responseCode = "404",
        description = "No existe ningún curso con el ID proporcionado.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class))),
    @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor.",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class)))
  })
  void delete(
      @Parameter(
              description = "ID único del curso a eliminar.",
              example = "cur-001",
              required = true)
          @PathVariable
          String id);
}
