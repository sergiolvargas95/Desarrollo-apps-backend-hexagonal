package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCursoRestRequest(
    @NotBlank(message = "nombre must not be blank")
        @Size(min = 3, message = "nombre must have at least 3 characters")
        String nombre,
    @NotBlank(message = "codigo must not be blank")
        @Size(min = 2, message = "codigo must have at least 2 characters")
        String codigo,
    @NotBlank(message = "descripcion must not be blank") String descripcion,
    @NotBlank(message = "institucion must not be blank")
        @Size(min = 3, message = "institucion must have at least 3 characters")
        String institucion,
    @NotBlank(message = "ubicacion must not be blank")
        @Size(min = 3, message = "ubicacion must have at least 3 characters")
        String ubicacion,
    @NotBlank(message = "modalidad must not be blank") String modalidad,
    @NotBlank(message = "nivel must not be blank") String nivel) {}
