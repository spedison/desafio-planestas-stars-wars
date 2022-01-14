package com.spedison.planetasstarwars.exception

class RegisterNotFoundException(
    mensagem: String = "",
    var id: Any = -1,
    var nomeClasse: String
) :
    RuntimeException(mensagem) {
    override fun toString(): String {
        return message + " - id = ${this.id.toString()}, classe = ${this.nomeClasse}"
    }

}

