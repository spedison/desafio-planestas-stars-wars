package com.spedison.planetasstarwars.dto.planeta

import io.micrometer.core.lang.NonNull
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class FormPlanetaAddRegiaoDTO(

    @field:NonNull @field:NotEmpty @field:Size(min = 3, max = 50)
    val nomeTerreno: String,
    @field:NonNull
    val trafegavelTerreno: Boolean,

    @field:NonNull @field:NotEmpty @field:Size(min = 3, max = 50)
    val nomeClima: String,
    @field:NonNull
    val viavelParaVidaClima: Boolean,

    @NonNull @NotEmpty @Size(min = 3, max = 50)
    val nomeRegiao: String
)
