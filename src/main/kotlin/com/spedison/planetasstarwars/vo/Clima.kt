package com.spedison.planetasstarwars.vo

import javax.persistence.*

@Entity
@Table(name ="tb_clima")
data class Clima(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(unique = true, length = 50)
    var nome: String,

    @Column(nullable = false)
    var viavelParaVida: Boolean,

    var ativo:Boolean=true,

    @OneToMany(mappedBy = "clima", fetch = FetchType.LAZY)
    var regioes: Collection<Regiao>

)
