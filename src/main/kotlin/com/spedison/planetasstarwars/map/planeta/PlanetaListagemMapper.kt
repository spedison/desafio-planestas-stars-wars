package com.spedison.planetasstarwars.map.planeta


import com.spedison.planetasstarwars.dto.planeta.FormPlanetaDTO
import com.spedison.planetasstarwars.dto.planeta.ViewPlanetaListagemDTO
import com.spedison.planetasstarwars.map.GenericMapperInterface
import com.spedison.planetasstarwars.vo.Planeta
import com.spedison.planetasstarwars.vo.Regiao
import org.springframework.stereotype.Component

@Component
class PlanetaListagemMapper : GenericMapperInterface<Planeta, FormPlanetaDTO, ViewPlanetaListagemDTO> {

    override fun mappeiaParaClasse(value: FormPlanetaDTO): Planeta =
        Planeta(null, value.nome, mutableListOf<Regiao>(), true)

    override fun mappeiaParaDTO(value: Planeta): ViewPlanetaListagemDTO =
        ViewPlanetaListagemDTO(value.id ?: -1, value.nome)

    fun mappeiaParaDTO(value: Planeta, nomeClima:String, nomeTerreno:String, nomeRegiao:String ): ViewPlanetaListagemDTO =
        ViewPlanetaListagemDTO(value.id ?: -1, value.nome, nomeClima, nomeTerreno, nomeRegiao)

}