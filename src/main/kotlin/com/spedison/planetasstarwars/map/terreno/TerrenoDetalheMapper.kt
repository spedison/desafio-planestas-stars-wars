package com.spedison.planetasstarwars.map.terreno

import com.spedison.planetasstarwars.dto.planeta.ViewPlanetaListagemDTO
import com.spedison.planetasstarwars.dto.terreno.FormTerrenoDTO
import com.spedison.planetasstarwars.dto.terreno.ViewTerrenoDTO
import com.spedison.planetasstarwars.dto.terreno.ViewTerrenoDetalheDTO
import com.spedison.planetasstarwars.map.GenericMapperInterface
import com.spedison.planetasstarwars.map.planeta.PlanetaListagemMapper
import com.spedison.planetasstarwars.vo.Terreno
import com.spedison.planetasstarwars.vo.Regiao
import org.springframework.stereotype.Component

@Component
class TerrenoDetalheMapper(val planetaMapper: PlanetaListagemMapper) :
    GenericMapperInterface<Terreno, FormTerrenoDTO, ViewTerrenoDetalheDTO> {

    override fun mappeiaParaClasse(value: FormTerrenoDTO): Terreno =
        Terreno(null, value.nome, value.trafegavel as Boolean, true, mutableListOf<Regiao>())

    override fun mappeiaParaDTO(terreno: Terreno): ViewTerrenoDetalheDTO {
        val planetas: List<ViewPlanetaListagemDTO> =  terreno
                .regioes
                .filter { regiao -> regiao.ativo }
                .map { regiao -> regiao.planeta }
                .filterNotNull()
                .filter { planeta -> planeta.ativo }
                .map { planeta -> planetaMapper.mappeiaParaDTO(planeta) }
                .toList()

        return ViewTerrenoDetalheDTO(terreno.id ?: -1, terreno.nome, terreno.trafegavel, planetas)
    }

}