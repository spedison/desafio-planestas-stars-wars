package com.spedison.planetasstarwars.controller

import com.spedison.planetasstarwars.dto.clima.ViewClimaDTO
import com.spedison.planetasstarwars.dto.clima.FormClimaDTO
import com.spedison.planetasstarwars.service.ClimaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/clima")
class ClimaController(var climaService: ClimaService) {

    @GetMapping()
    fun listaTodos(): List<ViewClimaDTO> =
        this.climaService.listaTodos()

    @GetMapping("/{id}")
    fun listaUm(
        @PathVariable(name = "id", required = true) id: Long,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<ViewClimaDTO> {
        val ret = this.climaService.listaUm(id)
        val uri = uriBuilder.path("/clima/${ret.id}").build().toUri()
        return ResponseEntity.status(HttpStatus.OK).location(uri).body(ret)
    }

    @PostMapping()
    fun adiciona(
        @RequestBody @Valid formulario: FormClimaDTO,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<ViewClimaDTO> {
        val ret = this.climaService.adiciona(formulario)
        val uri = uriBuilder.path("/clima/${ret.id}").build().toUri()
        return ResponseEntity.created(uri).body(ret)
    }


    @PatchMapping("/{id}")
    @PutMapping("/{id}")
    fun edita(
        @RequestBody @Valid formulario: FormClimaDTO,
        @PathVariable(name = "id", required = true) id: Long
    ): ViewClimaDTO = climaService.edita(formulario, id)


    @DeleteMapping("/{id}")
    fun apaga(
        @PathVariable(name = "id", required = true) id: Long
    ): ViewClimaDTO = climaService.apaga(id)

}