package com.spedison.planetasstarwars.repository

import com.spedison.planetasstarwars.vo.Planeta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PlanetaRepository : JpaRepository<Planeta, Long> {

    // TODO: Otimizar para realizar somente 1 query, pois aqui ele irá fazer umas 3 queries.
    fun findByIdAndAtivo(id: Long, ativo:Boolean) : Optional<Planeta>

    @Query("SELECT DISTINCT p " +
            "FROM " +
            "   Planeta p " +
            "   JOIN FETCH p.regioes r " +
            "   JOIN FETCH r.clima c " +
            "   JOIN FETCH r.terreno t "+
            "where c.nome like :nomeClima " +
            "and p.ativo = true and r.clima.ativo = true and t.ativo = true")
    fun findNomeClima(@Param("nomeClima") nomeClima: String): List<Planeta>

}