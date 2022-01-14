package com.spedison.planetasstarwars.dto.regiao

import com.spedison.planetasstarwars.dto.clima.ViewClimaDTO
import com.spedison.planetasstarwars.dto.terreno.ViewTerrenoDTO

data class ViewRegiaoDTO(
    val id:Long,
    val nome: String,
    val clima: ViewClimaDTO,
    val terreno: ViewTerrenoDTO
)
