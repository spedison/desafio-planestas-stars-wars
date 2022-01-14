package com.spedison.planetasstarwars.map.clima.implementation.mapper

import com.spedison.planetasstarwars.dto.clima.FormClimaDTO
import com.spedison.planetasstarwars.map.ClimaMapper
import com.spedison.planetasstarwars.vo.Clima
import com.spedison.planetasstarwars.vo.Regiao
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ClimaMapperTest {

    var climaMapper = ClimaMapper()
    val nome = "Maior Que 60oC"
    val viavelParaVida = true
    val idClima = 10L


    @BeforeEach
    fun setUp() {

    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun mappeiaParaClasse() {
        val clima = climaMapper.mappeiaParaClasse(
            FormClimaDTO(nome, viavelParaVida)
        )

        Assertions.assertEquals(clima.nome, nome)
        Assertions.assertEquals(clima.viavelParaVida, viavelParaVida)
    }

    @Test
    fun mappeiaParaDTO() {

        val climaVO: Clima = Clima(idClima, nome, viavelParaVida, true, mutableListOf<Regiao>())

        var climaDTO = climaMapper.mappeiaParaDTO(
            climaVO
        )

        Assertions.assertEquals(climaVO.nome, climaDTO.nome)
        Assertions.assertEquals(climaVO.viavelParaVida, climaDTO.viavelParaVida)
        Assertions.assertEquals(climaVO.id, climaDTO.id)

    }
}