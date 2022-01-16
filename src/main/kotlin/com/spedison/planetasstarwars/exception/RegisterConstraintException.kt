package com.spedison.planetasstarwars.exception

class RegisterConstraintException(
    mensagem: String = "",
    var id: Any = -1,
    var nomeClasse: String
) :
    RuntimeException(mensagem) {
    override fun toString(): String {
        return message + " - id = ${this.id.toString()}, classe = ${this.nomeClasse}"
    }

}

