package com.spedison.planetasstarwars.dto.terreno

import com.spedison.planetasstarwars.dto.planeta.ViewPlanetaListagemDTO

data class ViewTerrenoDetalheDTO(
    val id: Long,
    val nome: String,
    val trafegavel: Boolean,
    val planetas: List<ViewPlanetaListagemDTO>
)