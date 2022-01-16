package com.spedison.planetasstarwars.map.clima


import com.spedison.planetasstarwars.dto.clima.ViewListatemClimaDTO
import com.spedison.planetasstarwars.dto.clima.FormClimaDTO
import com.spedison.planetasstarwars.map.GenericMapperInterface
import com.spedison.planetasstarwars.vo.Clima
import com.spedison.planetasstarwars.vo.Regiao
import org.springframework.stereotype.Component

@Component
class ClimaViewMapper : GenericMapperInterface<Clima, FormClimaDTO, ViewListatemClimaDTO> {

    override fun mappeiaParaClasse(value: FormClimaDTO): Clima =
        Clima(null, value.nome, value.viavelParaVida as Boolean, true, mutableListOf<Regiao>())

    override fun mappeiaParaDTO(value: Clima): ViewListatemClimaDTO =
        ViewListatemClimaDTO(value.id?:-1, value.nome, value.viavelParaVida)

}