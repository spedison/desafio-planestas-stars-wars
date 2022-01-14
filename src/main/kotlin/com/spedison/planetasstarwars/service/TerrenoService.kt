package com.spedison.planetasstarwars.service

import com.spedison.planetasstarwars.dto.terreno.FormTerrenoDTO
import com.spedison.planetasstarwars.dto.terreno.ViewTerrenoDTO
import com.spedison.planetasstarwars.exception.RegisterNotFoundException
import com.spedison.planetasstarwars.map.GenericMapperInterface
import com.spedison.planetasstarwars.repository.TerrenoRepository
import com.spedison.planetasstarwars.vo.Terreno
import org.jvnet.hk2.annotations.Service
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.streams.toList

@Component
class TerrenoService (
    var repository: TerrenoRepository,
    var mapper: GenericMapperInterface<Terreno, FormTerrenoDTO, ViewTerrenoDTO>
) {

    fun listaTodos(): List<ViewTerrenoDTO> =
        repository
            .findAllByAtivo(true)
            .stream()
            .map(mapper::mappeiaParaDTO)
            .toList()

    @Throws(RegisterNotFoundException::class)
    fun listaUm(id: Long): ViewTerrenoDTO {

        val terreno = repository.findByIdAndAtivo(id, true)

        terreno.orElseThrow {
            RegisterNotFoundException(
                "listaUm",
                id = id,
                nomeClasse = Terreno::class.java.toString()
            )
        }

        return mapper.mappeiaParaDTO(terreno.get())
    }

    @Transactional
    fun adiciona(valor: FormTerrenoDTO): ViewTerrenoDTO {
        var terreno: Terreno = mapper.mappeiaParaClasse(valor)
        repository.save(terreno)
        return mapper.mappeiaParaDTO(terreno)
    }

    @Transactional
    fun edita(form: FormTerrenoDTO, id: Long): ViewTerrenoDTO {

        var terreno: Optional<Terreno> = repository.findByIdAndAtivo(id,true)

        terreno.orElseThrow {
            RegisterNotFoundException(
                "edita",
                id = id,
                nomeClasse = terreno::class.java.toString()
            )
        }

        terreno.get().let {
            it.nome = form.nome
            it.trafegavel = form.trafegavel
            repository.save(it)
        }

        return mapper.mappeiaParaDTO(terreno.get())
    }

    @Transactional
    fun apaga(id: Long): ViewTerrenoDTO {

        var terreno: Optional<Terreno> = repository.findById(id)

        //TODO: Se o terreno já foi utilizado em alguma região ativa, ele não pode ser apagado.impoddsss

        terreno.orElseThrow {
            RegisterNotFoundException(
                "apaga",
                id = id,
                nomeClasse = terreno::class.java.toString()
            )
        }

        terreno.get().let {
            it.ativo = false
            repository.save(it)
        }

        return mapper.mappeiaParaDTO(terreno.get())
    }

}