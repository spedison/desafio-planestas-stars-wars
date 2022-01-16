package com.spedison.planetasstarwars.map.clima


import com.spedison.planetasstarwars.dto.clima.ViewListatemClimaDTO
import com.spedison.planetasstarwars.dto.clima.FormClimaDTO
import com.spedison.planetasstarwars.dto.clima.ViewDetalheClimaDTO
import com.spedison.planetasstarwars.dto.planeta.ViewPlanetaListagemDTO
import com.spedison.planetasstarwars.map.GenericMapperInterface
import com.spedison.planetasstarwars.map.planeta.PlanetaListagemMapper
import com.spedison.planetasstarwars.vo.Clima
import com.spedison.planetasstarwars.vo.Planeta
import com.spedison.planetasstarwars.vo.Regiao
import org.springframework.stereotype.Component
import kotlin.streams.toList

@Component
class ClimaDetalheMapper(val planetaMapper: PlanetaListagemMapper) :
    GenericMapperInterface<Clima, FormClimaDTO, ViewDetalheClimaDTO> {

    override fun mappeiaParaClasse(value: FormClimaDTO): Clima =
        throw IllegalCallerException("A partir do DTO não é possível mappear o objeto neste caso.")


    override fun mappeiaParaDTO(clima: Clima): ViewDetalheClimaDTO {
        val listaPlanetas = clima
            .regioes
            .filter { regiao -> regiao.ativo }
            .map({ regiao -> regiao.planeta })
            .filterNotNull()
            .filter { planeta -> planeta.ativo }
            .map({ planeta -> planetaMapper.mappeiaParaDTO(planeta) })
            .toList()
        return ViewDetalheClimaDTO(clima.id as Long, clima.nome, clima.viavelParaVida, listaPlanetas)
    }

}