package com.spedison.planetasstarwars.controller

import com.spedison.planetasstarwars.dto.terreno.FormTerrenoDTO
import com.spedison.planetasstarwars.dto.terreno.ViewTerrenoDTO
import com.spedison.planetasstarwars.dto.terreno.ViewTerrenoDetalheDTO
import com.spedison.planetasstarwars.service.TerrenoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/terreno")
class TerrenoController(var terrenoService: TerrenoService) {

    @GetMapping()
    fun listaTodos(): List<ViewTerrenoDTO> =
        terrenoService.listaTodos()

    @GetMapping("/{id}")
    fun listaUm(
        @PathVariable(name = "id", required = true) id: Long,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<ViewTerrenoDetalheDTO> {
        val ret = this.terrenoService.listaUm(id)
        val uri = uriBuilder.path("/terreno/${ret.id}").build().toUri()
        return ResponseEntity.status(HttpStatus.OK).location(uri).body(ret)
    }


    @PostMapping()
    fun adiciona(
        @RequestBody @Valid formulario: FormTerrenoDTO,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<ViewTerrenoDTO> {
        val ret = this.terrenoService.adiciona(formulario)
        val uri = uriBuilder.path("/terreno/${ret.id}").build().toUri()
        return ResponseEntity.created(uri).body(ret)
    }


    @PutMapping("/{id}")
    fun edita(
        @RequestBody @Valid formulario: FormTerrenoDTO,
        @PathVariable(name = "id", required = true) id: Long
    ): ViewTerrenoDTO = terrenoService.edita(formulario, id)


    @DeleteMapping("/{id}")
    fun apaga(
        @PathVariable(name = "id", required = true) id: Long
    ): ViewTerrenoDTO = terrenoService.apaga(id)


}