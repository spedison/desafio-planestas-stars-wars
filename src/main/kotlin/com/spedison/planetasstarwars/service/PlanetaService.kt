package com.spedison.planetasstarwars.service

import com.spedison.planetasstarwars.dto.planeta.FormPlanetaAddRegiaoDTO
import com.spedison.planetasstarwars.dto.planeta.FormPlanetaDTO
import com.spedison.planetasstarwars.dto.planeta.ViewPlanetaDetalheDTO
import com.spedison.planetasstarwars.dto.planeta.ViewPlanetaListagemDTO
import com.spedison.planetasstarwars.exception.RegisterNotFoundException
import com.spedison.planetasstarwars.map.planeta.PlanetaDetalheMapper
import com.spedison.planetasstarwars.map.planeta.PlanetaListagemMapper
import com.spedison.planetasstarwars.repository.ClimaRepository
import com.spedison.planetasstarwars.repository.PlanetaRepository
import com.spedison.planetasstarwars.repository.RegiaoRepository
import com.spedison.planetasstarwars.repository.TerrenoRepository
import com.spedison.planetasstarwars.vo.Clima
import com.spedison.planetasstarwars.vo.Planeta
import com.spedison.planetasstarwars.vo.Regiao
import com.spedison.planetasstarwars.vo.Terreno
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Component
class PlanetaService(
    var planetaRepository: PlanetaRepository,
    var regiaoRepository: RegiaoRepository,
    var climaRepository: ClimaRepository,
    var terrenoRepository: TerrenoRepository,
    var planetaMapperDetalhe: PlanetaDetalheMapper,
    var planetaMapperListagem: PlanetaListagemMapper,
) {

    fun listaUm(id: Long): ViewPlanetaDetalheDTO {

        val clima = planetaRepository.findByIdAndAtivo(id, true)

        clima.orElseThrow {
            RegisterNotFoundException(
                "listaUm",
                id = id,
                nomeClasse = "Planeta"
            )
        }

        return planetaMapperDetalhe.mappeiaParaDTO(clima.get())
    }

    @Transactional
    fun adiciona(formulario: FormPlanetaDTO): ViewPlanetaListagemDTO {

        val regiao = criaRegiao(
            formulario.nomeClima,
            formulario.nomeTerreno,
            formulario.nomeRegiao,
            formulario.viavelParaVidaClima,
            formulario.trafegavelTerreno
        )

        // Crio o planeta.
        val planeta: Planeta = Planeta(null, formulario.nome, mutableListOf<Regiao>(regiao), true)

        // Salvo o planeta.
        planetaRepository.save(planeta)

        // Adicio a região ao planeta.
        regiao.planeta = planeta
        regiaoRepository.save(regiao)

        return planetaMapperListagem.mappeiaParaDTO(planeta,
            formulario.nomeClima,
            formulario.nomeTerreno,
            formulario.nomeRegiao)
    }

    private fun criaRegiao(
        nomeClima: String,
        nomeTerreno: String,
        nomeRegiao: String,
        viavelParaVidaClima: Boolean,
        trafegavelTerreno: Boolean,
    ): Regiao {
        val optClima = climaRepository.findByNome(nomeClima)
        val optTerreno = terrenoRepository.findByNome(nomeTerreno)

        // Se o clima não existir, Cria.
        val clima: Clima = if (optClima.isPresent()) {
            optClima.get()
        } else {
            val cli: Clima =
                Clima(null, nomeClima, viavelParaVidaClima, true, mutableListOf<Regiao>())
            climaRepository.save(cli)
            cli
        }

        if (clima.ativo == false)
            throw RegisterNotFoundException("O registro já foi apagado e o nome do Clima não deve ser usado [${clima.nome}] ",
                clima.id as Any,
                "Planeta::Clima")

        // Se o terreno não existir, Cria.
        val terreno: Terreno = if (optTerreno.isPresent()) {
            optTerreno.get()
        } else {
            val ter: Terreno =
                Terreno(null, nomeTerreno, trafegavelTerreno, true, mutableListOf<Regiao>())
            terrenoRepository.save(ter)
            ter
        }

        if (terreno.ativo == false)
            throw RegisterNotFoundException("O registro já foi apagado e o nome do Terreno não deve ser usado [${terreno.nome}] ",
                terreno.id as Any,
                "Planeta::Terreno")

        // Crio a 1o região do planeta.
        val regiao = Regiao(null, nomeRegiao, null, true, clima, terreno)
        return regiao
    }

    @Transactional
    fun adicionaRegiao(idPlaneta: Long, formulario: FormPlanetaAddRegiaoDTO): ViewPlanetaListagemDTO {

        // Resgata o planeta.
        val planetaOpt: Optional<Planeta> = planetaRepository.findById(idPlaneta)
        if (planetaOpt.isEmpty) throw RegisterNotFoundException("AdicionaRegiao", idPlaneta, "Planeta")
        val planeta: Planeta = planetaOpt.get()

        val regiao = criaRegiao(
            formulario.nomeClima,
            formulario.nomeTerreno,
            formulario.nomeRegiao,
            formulario.viavelParaVidaClima,
            formulario.trafegavelTerreno
        )

        regiao.planeta = planeta

        // Salva a regiao.
        regiaoRepository.save(regiao)

        // Salvo o planeta.
        planetaRepository.save(planeta)


        return planetaMapperListagem.mappeiaParaDTO(
            planeta,
            formulario.nomeClima,
            formulario.nomeTerreno,
            formulario.nomeRegiao
        )
    }

    fun listaTodos(): List<ViewPlanetaListagemDTO> =
        planetaRepository
            .findAll()
            .map(planetaMapperListagem::mappeiaParaDTO)
            .toList()

    fun listaTodosPorNome(nome: String): List<ViewPlanetaDetalheDTO> =
        planetaRepository
            .findNomeClima(nome)
            .map(planetaMapperDetalhe::mappeiaParaDTO)
            .toList()

}
