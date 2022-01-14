package com.spedison.planetasstarwars.repository

import com.spedison.planetasstarwars.vo.Clima
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ClimaRepository : JpaRepository<Clima, Long> {

    fun findByIdAndAtivo(id:Long,ativo:Boolean) : Optional<Clima>
    fun findAllByAtivo(ativo: Boolean): List<Clima>
    fun findByNomeAndAtivo(nome:String, ativo:Boolean): Optional<Clima>
    fun findByNome(nome:String): Optional<Clima>
}