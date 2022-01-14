package com.spedison.planetasstarwars.exception

class RegisterDuplicatedException(
    mensagem: String = "",
    var id: Any = -1,
    var nomeClasse: String
) :
    RuntimeException(mensagem) {
    override fun toString(): String {
        return super.toString() + " - id = ${this.id.toString()}, classe = ${this.nomeClasse}"
    }

}

