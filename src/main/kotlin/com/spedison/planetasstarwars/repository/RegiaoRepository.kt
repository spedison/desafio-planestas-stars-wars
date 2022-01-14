package com.spedison.planetasstarwars.repository

import com.spedison.planetasstarwars.vo.Regiao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RegiaoRepository : JpaRepository<Regiao, Long> {
}