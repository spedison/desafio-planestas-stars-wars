package com.spedison.planetasstarwars.controller

import com.spedison.planetasstarwars.dto.planeta.FormPlanetaAddRegiaoDTO
import com.spedison.planetasstarwars.dto.planeta.FormPlanetaDTO
import com.spedison.planetasstarwars.dto.planeta.ViewPlanetaDetalheDTO
import com.spedison.planetasstarwars.dto.planeta.ViewPlanetaListagemDTO
import com.spedison.planetasstarwars.service.PlanetaService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/planeta")
class PlanetaController(val planetaService: PlanetaService) {

    @GetMapping("/{id}")
    fun listaUm(
        @PathVariable(name = "id", required = true) id: Long,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<ViewPlanetaDetalheDTO> {
        val ret = this.planetaService.listaUm(id)
        val uri = uriBuilder.path("/planeta/${ret.id}").build().toUri()
        return ResponseEntity.status(HttpStatus.OK).location(uri).body(ret)
    }

    @GetMapping()
    fun listaPorNomePlaneta(
        @RequestParam(name = "filtro", required = false, defaultValue = "") filtro: String,
        @RequestParam(name = "contem", required = false) contem: Boolean,
        @PageableDefault(size = 20, page = 0, sort = ["id"], direction = Sort.Direction.ASC) page: Pageable,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<Page<ViewPlanetaListagemDTO>> {
        val ret = this.planetaService.listaTodosPorNomePlaneta(filtro,contem, page)
        val uri = uriBuilder.path("/planeta").build().toUri()
        return ResponseEntity.status(HttpStatus.OK).location(uri).body(ret)
    }

    @GetMapping("/clima")
    fun listaPorNomeClima(
        @RequestParam(name = "filtro", required = true) nomeDoClima: String,
        @RequestParam(name = "contem", required = false) contem: Boolean,
        @PageableDefault(size = 20, page = 0, sort = ["id"], direction = Sort.Direction.ASC) page: Pageable,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<Page<ViewPlanetaDetalheDTO>> {
        val ret = this.planetaService.listaTodosPorNomeClima(nomeDoClima, contem, page)
        val uri = uriBuilder.path("/planeta/clima?filtro=${nomeDoClima}").build().toUri()
        return ResponseEntity.status(HttpStatus.OK).location(uri).body(ret)
    }


    @GetMapping("/terreno")
    fun listaPorNomeTerreno(
        @RequestParam(required = true, name = "filtro") nomeDoTerreno: String,
        @RequestParam(name = "contem", required = false) contem: Boolean,
        @PageableDefault(size = 20, page = 0, sort = ["id"], direction = Sort.Direction.ASC) page: Pageable,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<Page<ViewPlanetaDetalheDTO>> {
        val ret = this.planetaService.listaTodosPorNomeTerreno(nomeDoTerreno, contem, page)
        val uri = uriBuilder.path("/planeta/terreno?filtro=${nomeDoTerreno}").build().toUri()
        return ResponseEntity.status(HttpStatus.OK).location(uri).body(ret)
    }

    @PostMapping()
    fun adiciona(
        @RequestBody @Valid formulario: FormPlanetaDTO,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<ViewPlanetaListagemDTO> {
        val ret = this.planetaService.adiciona(formulario)
        val uri = uriBuilder.path("/planeta/${ret.id}").build().toUri()
        return ResponseEntity.created(uri).body(ret)
    }

    @PostMapping("/{id}")
    fun adicionaRegiao(
        @PathVariable("id") idPlaneta: Long,
        @RequestBody @Valid formulario: FormPlanetaAddRegiaoDTO,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<ViewPlanetaListagemDTO> {
        val ret = this.planetaService.adicionaRegiao(idPlaneta, formulario)
        val uri = uriBuilder.path("/planeta/${ret.id}").build().toUri()
        return ResponseEntity.created(uri).body(ret)
    }

    @DeleteMapping("/{idPlaneta}/regiao/{idRegiao}")
    fun adicionaRegiao(
        @PathVariable("idPlaneta") idPlaneta: Long,
        @PathVariable("idRegiao") idRegiao: Long,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<ViewPlanetaDetalheDTO> {
        val ret = this.planetaService.removeRegiao(idPlaneta, idRegiao)
        val uri = uriBuilder.path("/planeta/${idPlaneta}/regiao/${idRegiao}").build().toUri()
        return ResponseEntity.created(uri).body(ret)
    }


}