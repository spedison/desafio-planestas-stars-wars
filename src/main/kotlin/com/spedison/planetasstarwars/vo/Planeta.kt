package com.spedison.planetasstarwars.vo

import javax.persistence.*


@Entity
@Table(name = "tb_planeta")
class Planeta (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @Column(length = 255, nullable = false, unique = true)
    val nome: String,

    @OneToMany(mappedBy = "planeta")
    var regioes: MutableList<Regiao>,

    var ativo:Boolean=true
)