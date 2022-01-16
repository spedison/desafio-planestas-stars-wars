package com.spedison.planetasstarwars.dto.geral

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class FormAlteraNome (
    @field:NotNull @field:NotEmpty @field:NotBlank @field:Size(min = 3)
    val nome: String
)