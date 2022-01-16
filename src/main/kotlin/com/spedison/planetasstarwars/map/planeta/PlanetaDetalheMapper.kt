package com.spedison.planetasstarwars.map.planeta


import com.spedison.planetasstarwars.dto.planeta.FormPlanetaDTO
import com.spedison.planetasstarwars.dto.planeta.ViewPlanetaDetalheDTO
import com.spedison.planetasstarwars.map.GenericMapperInterface
import com.spedison.planetasstarwars.map.RegiaoMapper
import com.spedison.planetasstarwars.vo.Planeta
import com.spedison.planetasstarwars.vo.Regiao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PlanetaDetalheMapper :
    GenericMapperInterface<Planeta, FormPlanetaDTO, ViewPlanetaDetalheDTO> {
    @Autowired
    lateinit var regiaoMapper: RegiaoMapper


    override fun mappeiaParaClasse(value: FormPlanetaDTO): Planeta =
        Planeta(null, value.nome, mutableListOf<Regiao>(), true)


    override fun mappeiaParaDTO(value: Planeta): ViewPlanetaDetalheDTO {
        val regioes = value
            .regioes
            .map(regiaoMapper::mappeiaParaDTO)
            .toList()
        return ViewPlanetaDetalheDTO(value.id?:-1, value.nome, regioes)
    }
}