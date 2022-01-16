package com.spedison.planetasstarwars.vo

import javax.persistence.*

@Entity
@Table(name ="tb_terreno")
data class Terreno(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(unique = true, length = 50)
    var nome: String,

    var trafegavel: Boolean,

    var ativo:Boolean=true,

    @OneToMany(mappedBy="terreno", fetch = FetchType.LAZY)
    var regioes: Collection<Regiao>
)