package com.spedison.planetasstarwars.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/alo")
class Alo {

    @GetMapping()
    public fun alo(nome:String?) : String = "Alô ${nome}!!"

}