package com.spedison.planetasstarwars.map


import com.spedison.planetasstarwars.dto.clima.ViewClimaDTO
import com.spedison.planetasstarwars.dto.clima.FormClimaDTO
import com.spedison.planetasstarwars.vo.Clima
import com.spedison.planetasstarwars.vo.Regiao
import org.springframework.stereotype.Component

@Component
class ClimaMapper : GenericMapperInterface<Clima, FormClimaDTO, ViewClimaDTO> {

    override fun mappeiaParaClasse(value: FormClimaDTO): Clima =
        Clima(null, value.nome, value.viavelParaVida, true, mutableListOf<Regiao>())

    override fun mappeiaParaDTO(value: Clima): ViewClimaDTO =
        ViewClimaDTO(value.id?:-1, value.nome, value.viavelParaVida)

}