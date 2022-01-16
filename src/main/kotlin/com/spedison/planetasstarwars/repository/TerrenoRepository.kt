package com.spedison.planetasstarwars.repository

import com.spedison.planetasstarwars.vo.Clima
import com.spedison.planetasstarwars.vo.Terreno
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TerrenoRepository : JpaRepository<Terreno, Long> {

    fun findByIdAndAtivo(id: Long, ativo: Boolean): Optional<Terreno>
    fun findAllByAtivo(ativo: Boolean): List<Terreno>
    fun findByNomeAndAtivo(nome: String, ativo: Boolean): Optional<Terreno>
    fun findByNome(nome: String): Optional<Terreno>

    @Query("SELECT count(p)  " +
            "FROM " +
            "   Planeta p " +
            "   JOIN p.regioes r " +
            "   JOIN r.terreno t " +
            "where " +
            "   t.id = :id " +
            "   and p.ativo = true " +
            "   and t.ativo = true " +
            "   and r.ativo = true" )
    fun countPlanetasAtivosByID(@Param("id") id:Long):Long

    @Query("SELECT t  " +
            "FROM " +
            "   Terreno t" +
            "   JOIN FETCH t.regioes r " +
            "   JOIN FETCH r.planeta p " +
            "where " +
            "   t.id = :id " +
            "   and t.ativo = true " +
            "   and p.ativo = true " +
            "   and r.ativo = true" )
    fun findTerrenoEPlanetasByID(@Param("id") id:Long):Optional<Terreno>
}