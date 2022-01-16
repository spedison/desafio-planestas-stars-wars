package com.spedison.planetasstarwars

import com.spedison.planetasstarwars.dto.clima.ViewClimaDTO
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class PlanetasStarWarsApplication

fun main(args: Array<String>) {
	runApplication<PlanetasStarWarsApplication>(*args)
}
