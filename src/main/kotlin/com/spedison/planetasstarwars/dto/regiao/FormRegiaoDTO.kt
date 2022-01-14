package com.spedison.planetasstarwars.dto.regiao

import io.micrometer.core.lang.NonNull
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class FormRegiaoDTO(
    @NonNull @NotEmpty @Size(min = 3, max = 255)
    val nome: String,
    @NonNull @NotEmpty @Size(min = 3, max = 50)
    val nomeTerreno: String,
    @NonNull @NotEmpty @Size(min = 3, max = 50)
    val nomeClima: String,
    @NonNull @NotEmpty @Size(min = 3, max = 50)
    val nomeRegiao: String
)
