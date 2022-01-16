package com.spedison.planetasstarwars.dto.regiao

import com.spedison.planetasstarwars.dto.clima.ViewListatemClimaDTO
import com.spedison.planetasstarwars.dto.terreno.ViewTerrenoDTO

data class ViewRegiaoDTO(
    val id:Long,
    val nome: String,
    val clima: ViewListatemClimaDTO,
    val terreno: ViewTerrenoDTO
)
