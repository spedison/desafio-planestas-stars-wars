package com.spedison.planetasstarwars.service

import com.spedison.planetasstarwars.dto.clima.ViewClimaDTO
import com.spedison.planetasstarwars.dto.clima.FormClimaDTO
import com.spedison.planetasstarwars.exception.RegisterNotFoundException
import com.spedison.planetasstarwars.map.GenericMapperInterface
import com.spedison.planetasstarwars.repository.ClimaRepository
import com.spedison.planetasstarwars.vo.Clima
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.streams.toList

@Component
class ClimaService(
    val repository: ClimaRepository,
    var mapper: GenericMapperInterface<Clima, FormClimaDTO, ViewClimaDTO>,
) {

    fun listaTodos(): List<ViewClimaDTO> =
        repository
            .findAllByAtivo(true)
            .stream()
            .map(mapper::mappeiaParaDTO)
            .toList()


    fun listaUm(id: Long): ViewClimaDTO {

        val clima = repository.findByIdAndAtivo(id, true)

        clima.orElseThrow {
            RegisterNotFoundException(
                "listaUm",
                id = id,
                nomeClasse = Clima::class.java.toString()
            )
        }

        return mapper.mappeiaParaDTO(clima.get())
    }

    @Transactional
    fun adiciona(valor: FormClimaDTO): ViewClimaDTO {
        var clima: Clima = mapper.mappeiaParaClasse(valor)
        repository.save(clima)
        return mapper.mappeiaParaDTO(clima)
    }

    @Transactional
    fun edita(form: FormClimaDTO, id: Long): ViewClimaDTO {

        var clima: Optional<Clima> = repository.findByIdAndAtivo(id, true)

        clima.orElseThrow {
            RegisterNotFoundException(
                "edita",
                id = id,
                nomeClasse = Clima::class.java.toString()
            )
        }

        clima.get().let {
            it.nome = form.nome
            it.viavelParaVida = form.viavelParaVida
            repository.save(it)
        }

        return mapper.mappeiaParaDTO(clima.get())
    }

    @Transactional
    fun apaga(id: Long): ViewClimaDTO {

        //TODO: Se o clima está sendo utilizado por alguma região ativa, ele não deve ser apagado.

        var clima: Optional<Clima> = repository.findByIdAndAtivo(id, true)

        clima.orElseThrow {
            RegisterNotFoundException(
                "apaga",
                id = id,
                nomeClasse = Clima::class.java.toString()
            )
        }

        clima.get().let {
            it.ativo = false
            repository.save(it)
        }

        return mapper.mappeiaParaDTO(clima.get())
    }
}