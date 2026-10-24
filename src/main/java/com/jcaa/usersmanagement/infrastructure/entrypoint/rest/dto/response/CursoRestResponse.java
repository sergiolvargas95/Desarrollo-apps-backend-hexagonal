package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response;

public record CursoRestResponse(
    String id,
    String nombre,
    String codigo,
    String descripcion,
    String institucion,
    String ubicacion,
    String modalidad,
    String nivel) {}
