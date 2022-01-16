package com.spedison.planetasstarwars.dto.clima

import com.fasterxml.jackson.annotation.JacksonAnnotation
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


data class FormClimaDTO(
    @field:NotEmpty @field:NotNull @field:Size(min = 4, max = 50)
    var nome: String,
    @field:NotNull @NotNull
    var viavelParaVida: Boolean?
)