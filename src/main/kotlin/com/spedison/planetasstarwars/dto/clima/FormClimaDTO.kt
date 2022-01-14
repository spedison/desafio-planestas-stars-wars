package com.spedison.planetasstarwars.dto.clima

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


data class FormClimaDTO(
    @field:NotEmpty @field:NotNull @field:Size(min = 4, max = 50)
    var nome: String,
    @field:NotNull
    var viavelParaVida: Boolean
)