package com.spedison.planetasstarwars.repository

import com.spedison.planetasstarwars.vo.Regiao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RegiaoRepository : JpaRepository<Regiao, Long> {


    @Query(
        "Select r " +
        "from " +
        "  Regiao r " +
        "JOIN FETCH r.planeta p " +
        "where " +
        "  r.id = :idRegiao " +
        "  and p.id = :idPlaneta " +
        "  and r.ativo = true " +
        "  and p.ativo = true "
    )
    fun findByIdAAndPlanetaId(
        @Param("idRegiao") idRegiao: Long,
        @Param("idPlaneta") idPlaneta: Long,
    ): Optional<Regiao>

}