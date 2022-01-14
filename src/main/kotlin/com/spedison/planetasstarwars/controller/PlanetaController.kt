package com.spedison.planetasstarwars.controller

import com.spedison.planetasstarwars.dto.clima.ViewClimaDTO
import com.spedison.planetasstarwars.dto.planeta.FormPlanetaAddRegiaoDTO
import com.spedison.planetasstarwars.dto.planeta.FormPlanetaDTO
import com.spedison.planetasstarwars.dto.planeta.ViewPlanetaDetalheDTO
import com.spedison.planetasstarwars.dto.planeta.ViewPlanetaListagemDTO
import com.spedison.planetasstarwars.service.PlanetaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/planeta")
class PlanetaController (val planetaService: PlanetaService) {

    @GetMapping("/{id}")
    fun listaUm(
        @PathVariable(name = "id", required = true) id: Long,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<ViewPlanetaDetalheDTO> {
        val ret = this.planetaService.listaUm(id)
        val uri = uriBuilder.path("/planeta/${ret.id}").build().toUri()
        return ResponseEntity.status(HttpStatus.OK).location(uri).body(ret)
    }

    @GetMapping()
    fun listaUm(
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<List<ViewPlanetaListagemDTO>> {
        val ret = this.planetaService.listaTodos()
        val uri = uriBuilder.path("/planeta").build().toUri()
        return ResponseEntity.status(HttpStatus.OK).location(uri).body(ret)
    }

    @GetMapping("/clima/{nome}")
    fun listaPorNomeClima(
        @PathVariable("nome") nome: String,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<List<ViewPlanetaDetalheDTO>> {
        val ret = this.planetaService.listaTodosPorNome(nome)
        val uri = uriBuilder.path("/planeta/clima/${nome}").build().toUri()
        return ResponseEntity.status(HttpStatus.OK).location(uri).body(ret)
    }

    @PostMapping()
    fun adiciona(
        @RequestBody @Valid formulario: FormPlanetaDTO,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<ViewPlanetaListagemDTO> {
        val ret = this.planetaService.adiciona(formulario)
        val uri = uriBuilder.path("/planeta/${ret.id}").build().toUri()
        return ResponseEntity.created(uri).body(ret)
    }

    @PostMapping("/{id}")
    fun adicionaRegiao(
        @PathVariable("id")  idPlaneta:Long,
        @RequestBody @Valid formulario: FormPlanetaAddRegiaoDTO,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<ViewPlanetaListagemDTO> {
        val ret = this.planetaService.adicionaRegiao(idPlaneta, formulario)
        val uri = uriBuilder.path("/planeta/${ret.id}").build().toUri()
        return ResponseEntity.created(uri).body(ret)
    }

}