package com.spedison.planetasstarwars.repository

import com.spedison.planetasstarwars.vo.Terreno
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TerrenoRepository : JpaRepository<Terreno, Long> {

    fun findByIdAndAtivo(id: Long, ativo: Boolean): Optional<Terreno>
    fun findAllByAtivo(ativo: Boolean): List<Terreno>
    fun findByNomeAndAtivo(nome: String, ativo: Boolean): Optional<Terreno>
    fun findByNome(nome: String): Optional<Terreno>
}