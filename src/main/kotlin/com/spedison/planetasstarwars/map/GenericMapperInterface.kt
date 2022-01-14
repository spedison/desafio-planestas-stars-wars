package com.spedison.planetasstarwars.map

interface  GenericMapperInterface <Obj,FormDTO,ViewDTO> {
    fun mappeiaParaClasse(value: FormDTO): Obj
    fun mappeiaParaDTO(value: Obj): ViewDTO
}