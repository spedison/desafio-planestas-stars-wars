package com.spedison.planetasstarwars.repository

import com.spedison.planetasstarwars.vo.Clima
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ClimaRepository : JpaRepository<Clima, Long> {

    fun findByIdAndAtivo(id:Long,ativo:Boolean) : Optional<Clima>
    fun findAllByAtivo(ativo: Boolean): List<Clima>
    fun findByNomeAndAtivo(nome:String, ativo:Boolean): Optional<Clima>
    fun findByNome(nome:String): Optional<Clima>

    @Query("SELECT count(p)  " +
            "FROM " +
            "   Planeta p " +
            "   JOIN p.regioes r " +
            "   JOIN r.clima c " +
            "where " +
            "   c.id = :id " +
            "   and p.ativo = true " +
            "   and c.ativo = true " +
            "   and r.ativo = true" )
    fun countPlanetasAtivosByID(@Param("id") id:Long):Long


    @Query("SELECT c  " +
            "FROM " +
            "   Clima c" +
            "   JOIN FETCH c.regioes r " +
            "   JOIN FETCH r.planeta p " +
            "where " +
            "   c.id = :id " +
            "   and c.ativo = true " +
            "   and p.ativo = true " +
            "   and r.ativo = true" )
    fun findClimaEPlanetasByID(@Param("id") id:Long):Optional<Clima>
}