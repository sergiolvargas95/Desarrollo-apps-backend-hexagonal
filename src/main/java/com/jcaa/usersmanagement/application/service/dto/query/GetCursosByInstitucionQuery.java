package com.jcaa.usersmanagement.application.service.dto.query;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GetCursosByInstitucionQuery(
    @NotBlank(message = "institucion must not be blank")
        @Size(min = 3, message = "institucion must have at least 3 characters")
        String institucion) {

}
