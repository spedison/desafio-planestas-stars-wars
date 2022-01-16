package com.spedison.planetasstarwars.dto.terreno

import io.micrometer.core.lang.NonNull
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class FormTerrenoDTO(
    @field:NonNull @field:NotEmpty @field:Size(min = 3, max = 50)
    val nome: String,

    @field:NonNull @field:NotNull
    val trafegavel: Boolean?,
)