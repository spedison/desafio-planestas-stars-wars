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
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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

    val nomeClasse: String = PlanetaService::class.simpleName ?: ""

    fun listaUm(id: Long): ViewPlanetaDetalheDTO {

        val clima = planetaRepository.findByIdAndAtivo(id)

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

        val regiao = adicionaRegiao(
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

    private fun adicionaRegiao(
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

        val regiao = adicionaRegiao(
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

    fun listaTodosPorNomePlaneta(filtro: String, contem: Boolean, page: Pageable): Page<ViewPlanetaListagemDTO> {
        return if (filtro.trim().isEmpty()) {
            planetaRepository
                .findAll(page)
                .map(planetaMapperListagem::mappeiaParaDTO)
        } else {
            val nomeAjustado: String = if (contem) {
                "%${filtro}%"
            } else {
                "${filtro}"
            }
            planetaRepository
                .findNomePlaneta(nomeAjustado, page)
                .map(planetaMapperListagem::mappeiaParaDTO)
        }
    }

    fun listaTodosPorNomeClima(nome: String, contem: Boolean, page: Pageable): Page<ViewPlanetaDetalheDTO> {

        val nomeAjustado: String = if (contem) "%${nome}%" else nome

        return planetaRepository
            .findNomeClima(nomeAjustado, page)
            .map(planetaMapperDetalhe::mappeiaParaDTO)
    }

    fun listaTodosPorNomeTerreno(nome: String, contem: Boolean, page: Pageable): Page<ViewPlanetaDetalheDTO> {

        val nomeAjustado: String = if (contem) "%${nome}%" else nome

        return planetaRepository
            .findNomeTerreno(nomeAjustado, page)
            .map(planetaMapperDetalhe::mappeiaParaDTO)
    }

    fun removeRegiao(idPlaneta: Long, idRegiao: Long): ViewPlanetaDetalheDTO {

        val regiaoOpt = planetaRepository
            .findRegiaoDoPlaneta(idPlaneta, idRegiao)

        if (regiaoOpt.isEmpty) throw RegisterNotFoundException(
            "RemoveRegiao",
            "Planeta = $idPlaneta, regiao = $idRegiao" as Any,
            this.nomeClasse
        )

        val regiao: Regiao = regiaoOpt.get()
        regiao.ativo = false
        regiaoRepository.save(regiao)

        val planeta = planetaRepository
            .findByIdAndAtivo(idPlaneta)

        return planeta
            .get()
            .let(planetaMapperDetalhe::mappeiaParaDTO)
    }

    @Transactional
    fun alteraNomePlaneta(idPlaneta: Long, nome: String): ViewPlanetaListagemDTO {
        var planeta: Optional<Planeta> = planetaRepository.findById(idPlaneta)

        if (planeta.isEmpty) throw RegisterNotFoundException(
            "AlternaNomePlaneta",
            idPlaneta,
            this.nomeClasse
        )

        if (planeta.get().ativo == false) throw RegisterNotFoundException(
            "AlternaNomePlaneta - Não ativo",
            idPlaneta,
            this.nomeClasse
        )

        planeta.get().nome = nome
        planetaRepository.save(planeta.get())

        return planeta.let { it -> planetaMapperListagem.mappeiaParaDTO(it.get()) }
    }

    @Transactional
    fun alteraNomeRegiaoPlaneta(idPlaneta: Long, idRegiao: Long, nome: String): ViewPlanetaDetalheDTO {

        var regiao: Optional<Regiao> = regiaoRepository.findByIdAAndPlanetaId(idRegiao, idPlaneta)

        if (regiao.isEmpty) throw RegisterNotFoundException(
            "AlternaNomeRegiaoPlaneta",
            "Planeta = $idPlaneta Regiao = $idRegiao",
            this.nomeClasse
        )

        regiao.get().nome = nome
        regiaoRepository.save(regiao.get())

        val ret : ViewPlanetaDetalheDTO = planetaRepository
            .findByIdAndAtivo(idPlaneta)
            .map(planetaMapperDetalhe::mappeiaParaDTO)
            .get()

        return ret
    }

    fun removePlaneta(idPlaneta: Long) {
        var planeta: Optional<Planeta> = planetaRepository.findById(idPlaneta)

        if (planeta.isEmpty) throw RegisterNotFoundException(
            "RemoverPlaneta",
            idPlaneta,
            this.nomeClasse
        )

        if (planeta.get().ativo == false) throw RegisterNotFoundException(
            "RemoverPlaneta - Não ativo",
            idPlaneta,
            this.nomeClasse
        )


        // Resgata todas as regiões não nulas e ativas para desabilitar e manter a consistência.
        planeta.get().regioes
            .filterNotNull()
            .filter { r -> r.ativo }
            .forEach { it ->
                it.ativo = false
                regiaoRepository.save(it)
            }

        // Desativa o planeta.
        planeta.get().ativo = false
        planetaRepository.save(planeta.get())

    }

}
