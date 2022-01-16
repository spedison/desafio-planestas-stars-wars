package com.spedison.planetasstarwars.repository

import com.spedison.planetasstarwars.vo.Planeta
import com.spedison.planetasstarwars.vo.Regiao
import com.spedison.planetasstarwars.vo.Terreno
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PlanetaRepository : JpaRepository<Planeta, Long> {

    @Query("SELECT p " +
            "FROM " +
            "   Planeta p " +
            "   JOIN FETCH p.regioes r " +
            "   JOIN FETCH r.clima c " +
            "   JOIN FETCH r.terreno t " +
            "where " +
            "       p.id = :id " +
            "   and p.ativo = true " +
            "   and r.ativo = true " +
            "   and c.ativo = true " +
            "   and t.ativo = true")
    fun findByIdAndAtivo(@Param("id") id: Long): Optional<Planeta>

    /*
    @Query(value =  "SELECT t " +
            "FROM " +
            "   Terreno t " +
            "   LEFT JOIN FETCH t.regioes r " +
            "   LEFT JOIN FETCH r.planeta p " +
            "   LEFT JOIN p.regioes r2 " +
            "where " +
            "   t.nome like :nomeTerreno "
    )
    fun findNomeTerreno2(
        @Param("nomeTerreno") nomeTerreno: String //,
       /* @Param("id") id: Long*/
    ): List<Terreno>*/


    @Query("SELECT  p " +
            "FROM " +
            "   Planeta p " +
            "   JOIN FETCH p.regioes r " +
            "   JOIN FETCH r.clima c " +
            "   JOIN FETCH r.terreno t " +
            "where " +
            "   c.nome like :nomeClima " +
            "   and p.ativo = true " +
            "   and r.ativo = true " +
            "   and c.ativo = true " +
            "   and t.ativo = true",
        countQuery = "SELECT count(p) " +
                "FROM " +
                "   Planeta p " +
                "   JOIN p.regioes r " +
                "   JOIN r.clima c " +
                "where " +
                "   c.nome like :nomeClima " +
                "   and p.ativo = true " +
                "   and r.ativo = true " +
                "   and c.ativo = true " )
    fun findNomeClima(@Param("nomeClima") nomeClima: String, page: Pageable): Page<Planeta>

/*
    @Query("SELECT t " +
            "FROM " +
            "   Terreno t " +
            "   LEFT JOIN FETCH t.regioes r " +
            "   LEFT JOIN FETCH r.clima c " +
            "   LEFT JOIN FETCH r.planeta p " +
            "where " +
            "   t.nome like :nomeTerreno  " +
            "   and p.ativo = true " +
            "   and r.ativo = true " +
            "   and c.ativo = true " +
            "   and t.ativo = true",
        countQuery = "select count(t) from Terreno t where t.nome like :nomeTerreno and t.ativo = true "
    )
    fun findNomeTerreno3(@Param("nomeTerreno") nomeTerreno: String, page: Pageable): Page<Terreno>*/

/*
    @Query("SELECT distinct t " +
            "FROM " +
            "   Terreno t " +
            "   LEFT JOIN FETCH t.regioes r " +
//            "   LEFT JOIN FETCH r.clima c " +
            "   LEFT JOIN FETCH r.planeta p " +
            "where " +
            "   t.nome like :nomeTerreno  " +
//            "   and p.ativo = true " +
            "   and r.ativo = true " +
//            "   and c.ativo = true " +
            "   and t.ativo = true",
        countQuery = "SELECT count(t) " +
        "FROM " +
                "   Terreno t " +
//              "   LEFT JOIN FETCH t.regioes r " +
//                "   LEFT JOIN FETCH r.clima c " +
//                "   LEFT JOIN FETCH r.planeta p " +
                "where " +
                "   t.nome like :nomeTerreno  " +
//                "   and p.ativo = true " +
//                "   and r.ativo = true " +
//                "   and c.ativo = true " +
                "   and t.ativo = true"
    )
    fun findNomeTerreno4(@Param("nomeTerreno") nomeTerreno: String, page: Pageable): Page<Terreno>*/

/*    @Query("SELECT p " +
            "FROM " +
            "   Planeta p " +
            "   LEFT JOIN FETCH p.regioes r " +
            "   LEFT JOIN FETCH r.clima c " +
            "   LEFT JOIN FETCH r.terreno t " +
            "where " +
            "   t.nome like :nomeTerreno and " +
            "   p.ativo = true " +
            "   and r.ativo = true " +
            "   and c.ativo = true " +
            "   and t.ativo = true "
,

        countQuery = "SELECT count(p) " +
                "FROM " +
                "   Planeta p " +

                "where " +
//            "   t.nome like :nomeTerreno  and" +
                "   p.ativo = true " //+
//                "   and r.ativo = true " +
//                "   and c.ativo = true " +
//                "   and t.ativo = true "
    )
    fun findNomeTerreno5(@Param("nomeTerreno") nomeTerreno: String, page: Pageable): Page<Planeta>*/


    @Query("SELECT p " +
            "FROM " +
            "   Planeta p " +
            "   LEFT JOIN FETCH  p.regioes r " +
            "   LEFT JOIN FETCH  r.clima c " +
            "   LEFT JOIN FETCH  r.terreno t " +
            "where " +
            "   t.nome like :nomeTerreno and " +
            "       p.ativo = true " +
            "   and r.ativo = true " +
            "   and c.ativo = true " +
            "   and t.ativo = true",

        countQuery = "SELECT count(p) " +
                "FROM " +
                "   Planeta p " +
                "   LEFT JOIN  p.regioes r " +
                "   LEFT JOIN  r.clima c " +
                "   LEFT JOIN  r.terreno t " +
                "where " +
                "   t.nome like :nomeTerreno and " +
                "       p.ativo = true " +
                "   and r.ativo = true " +
                "   and c.ativo = true " +
                "   and t.ativo = true"
    )
    fun findNomeTerreno(@Param("nomeTerreno") nomeTerreno: String, page: Pageable): Page<Planeta>


    @Query("SELECT DISTINCT p " +
            "FROM " +
            "   Planeta p " +
            "   JOIN p.regioes r " +
            "   JOIN r.clima c " +
            "   JOIN r.terreno t " +
            "where " +
            "   p.nome like :nomePlaneta " +
            "   and p.ativo = true " +
            "   and r.ativo = true " +
            "   and c.ativo = true " +
            "   and t.ativo = true")
    fun findNomePlaneta(@Param("nomePlaneta") nomePlaneta: String, page: Pageable): Page<Planeta>

    @Query("SELECT r " +
            "FROM " +
            "   Regiao r " +
            "   JOIN FETCH r.planeta p " +
            "where " +
            "   p.id = :idPlaneta " +
            "   and r.id = :idRegiao " +
            "   and p.ativo = true " +
            "   and r.ativo = true")
    fun findRegiaoDoPlaneta(@Param("idPlaneta")  idPlaneta: Long,
                            @Param("idRegiao")  idRegiao: Long): Optional<Regiao>

    @Query("UPDATE Regiao " +
            "SET ativo = false " +
            "where " +
            "   id = :idRegiao " +
            "   and planeta.id = :idPlaneta" )
    fun deleteRegiaoDoPlaneta(@Param("idPlaneta")  idPlaneta: Long,
                            @Param("idRegiao")  idRegiao: Long): Unit


}