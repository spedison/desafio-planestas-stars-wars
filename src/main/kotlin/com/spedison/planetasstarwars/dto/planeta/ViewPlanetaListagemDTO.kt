package com.spedison.planetasstarwars.dto.planeta

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ViewPlanetaListagemDTO(
    val id:Long,
    val nome: String,
    var nomeClima: String? = null,
    var nomeTerreno: String? = null,
    var nomeRegiao: String? = null
)
