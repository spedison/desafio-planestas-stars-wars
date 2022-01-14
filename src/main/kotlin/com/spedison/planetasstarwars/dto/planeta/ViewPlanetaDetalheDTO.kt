package com.spedison.planetasstarwars.dto.planeta

import com.spedison.planetasstarwars.dto.regiao.ViewRegiaoDTO

data class ViewPlanetaDetalheDTO(
    val id: Long,
    val nome: String,
    val regioes: List<ViewRegiaoDTO>,
)
