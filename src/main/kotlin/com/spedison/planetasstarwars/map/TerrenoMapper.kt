package com.spedison.planetasstarwars.map

import com.spedison.planetasstarwars.dto.terreno.FormTerrenoDTO
import com.spedison.planetasstarwars.dto.terreno.ViewTerrenoDTO
import com.spedison.planetasstarwars.vo.Terreno
import com.spedison.planetasstarwars.vo.Regiao
import org.springframework.stereotype.Component

@Component
class TerrenoMapper : GenericMapperInterface<Terreno, FormTerrenoDTO, ViewTerrenoDTO> {

    override fun mappeiaParaClasse(value: FormTerrenoDTO): Terreno =
        Terreno(null, value.nome, value.trafegavel as Boolean, true, mutableListOf<Regiao>())

    override fun mappeiaParaDTO(value: Terreno): ViewTerrenoDTO =
        ViewTerrenoDTO(value.id?:-1, value.nome, value.trafegavel)

}