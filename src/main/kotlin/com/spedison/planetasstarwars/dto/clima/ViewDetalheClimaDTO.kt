package com.spedison.planetasstarwars.dto.clima

import com.spedison.planetasstarwars.dto.planeta.ViewPlanetaListagemDTO


data class ViewDetalheClimaDTO(
    var id: Long ,
    var nome: String,
    var viavelParaVida: Boolean,
    val planetas : List<ViewPlanetaListagemDTO>
)