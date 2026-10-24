package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record CursoPersistenceDto(
    String id,
    String nombre,
    String codigo,
    String descripcion,
    String institucion,
    String ubicacion,
    String modalidad,
    String nivel,
    String createdAt,
    String updatedAt) {}
