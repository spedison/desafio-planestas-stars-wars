package com.spedison.planetasstarwars.map


import com.spedison.planetasstarwars.dto.regiao.FormRegiaoDTO
import com.spedison.planetasstarwars.dto.regiao.ViewRegiaoDTO
import com.spedison.planetasstarwars.map.clima.ClimaViewMapper
import com.spedison.planetasstarwars.map.terreno.TerrenoMapper
import com.spedison.planetasstarwars.vo.Regiao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RegiaoMapper : GenericMapperInterface<Regiao, FormRegiaoDTO, ViewRegiaoDTO> {


    @Autowired
    lateinit var climaMapper: ClimaViewMapper

    @Autowired
    lateinit var terrenoMapper: TerrenoMapper

    override fun mappeiaParaClasse(value: FormRegiaoDTO): Regiao = TODO()

    override fun mappeiaParaDTO(value: Regiao): ViewRegiaoDTO {
        val clima = climaMapper.mappeiaParaDTO(value.clima)
        val terreno = terrenoMapper.mappeiaParaDTO(value.terreno)
        return ViewRegiaoDTO(value.id ?: -1, value.nome, clima, terreno)
    }


}