package com.spedison.planetasstarwars.service

import com.spedison.planetasstarwars.dto.terreno.FormTerrenoDTO
import com.spedison.planetasstarwars.dto.terreno.ViewTerrenoDTO
import com.spedison.planetasstarwars.exception.RegisterConstraintException
import com.spedison.planetasstarwars.exception.RegisterNotFoundException
import com.spedison.planetasstarwars.map.GenericMapperInterface
import com.spedison.planetasstarwars.repository.TerrenoRepository
import com.spedison.planetasstarwars.vo.Terreno
import org.jvnet.hk2.annotations.Service
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.streams.toList

@Component
class TerrenoService(
    var repository: TerrenoRepository,
    var mapper: GenericMapperInterface<Terreno, FormTerrenoDTO, ViewTerrenoDTO>,
) {

    private val nomeClasse : String = this::class.simpleName?:""

    @Cacheable("terrenoTodos")
    fun listaTodos(): List<ViewTerrenoDTO> =
        repository
            .findAllByAtivo(true)
            .stream()
            .map(mapper::mappeiaParaDTO)
            .toList()

    @Cacheable("terrenoUnico")
    fun listaUm(id: Long): ViewTerrenoDTO {

        val terreno = repository.findByIdAndAtivo(id, true)

        terreno.orElseThrow {
            RegisterNotFoundException(
                "listaUm",
                id = id,
                nomeClasse = nomeClasse
            )
        }

        return mapper.mappeiaParaDTO(terreno.get())
    }

    @Transactional
    @CacheEvict(value=["terrenoTodos","terrenoUnico"], allEntries = true)
    fun adiciona(valor: FormTerrenoDTO): ViewTerrenoDTO {
        var terreno: Terreno = mapper.mappeiaParaClasse(valor)
        repository.save(terreno)
        return mapper.mappeiaParaDTO(terreno)
    }

    @Transactional
    @CacheEvict(value=["terrenoTodos","terrenoUnico"], allEntries = true)
    fun edita(form: FormTerrenoDTO, id: Long): ViewTerrenoDTO {

        var terreno: Optional<Terreno> = repository.findByIdAndAtivo(id, true)

        terreno.orElseThrow {
            RegisterNotFoundException(
                "edita",
                id = id,
                nomeClasse = nomeClasse
            )
        }

        terreno.get().let {
            it.nome = form.nome
            it.trafegavel = form.trafegavel as Boolean
            repository.save(it)
        }

        return mapper.mappeiaParaDTO(terreno.get())
    }

    @Transactional
    @CacheEvict(value=["terrenoTodos","terrenoUnico"], allEntries = true)
    fun apaga(id: Long): ViewTerrenoDTO {

        var terreno: Optional<Terreno> = repository.findByIdAndAtivo(id,true)

        terreno.orElseThrow {
            RegisterNotFoundException(
                "apaga",
                id = id,
                nomeClasse = nomeClasse
            )
        }

        if (repository.countPlanetasAtivosByID(terreno.get().id ?: -1) > 0)
            throw RegisterConstraintException(
                "Registro de Terreno está sendo utilizado no planeta.",
                terreno.get().id ?: -1,
                nomeClasse)

        terreno.get().let {
            it.ativo = false
            repository.save(it)
        }

        return mapper.mappeiaParaDTO(terreno.get())
    }

}