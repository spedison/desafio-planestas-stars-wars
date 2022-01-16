package com.spedison.planetasstarwars.advice

import com.fasterxml.jackson.core.JsonParseException
import com.spedison.planetasstarwars.exception.RegisterConstraintException
import com.spedison.planetasstarwars.exception.RegisterNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.sql.SQLException
import java.util.stream.Stream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.streams.toList


@ControllerAdvice
class ErrorHandler {

    private val chave1: String = "Duplicate entry "
    private val chave2: String = " for key "


    private fun extraiValorRepetido(str: String?): String {
        //(conn=50) Duplicate entry 'Extremo Norte' for key 'UK_48yc9k7j2doxt12nl4h7dn8tp'

        if (str == null) return ""

        val pos1 = str.indexOf(chave1, 0, false)
        if (pos1 == -1) return ""

        val strRight = str.subSequence(pos1 + chave1.length, str.length)
        val pos2 = strRight.lastIndexOf(chave2, strRight.length - 1, false)
        if (pos2 == -1) return ""

        return strRight.substring(0, pos2)
    }

    @ExceptionHandler(JsonParseException::class)
    fun JsonParseExceptionHandler(
        servletRequest: HttpServletRequest,
        servletResponse: HttpServletResponse,
        exception: Exception,
    ):
            ResponseEntity<ErrorMessage> {
        return ResponseEntity(ErrorMessage("JSON ERROR", exception.message ?: "invalid json"), HttpStatus.BAD_REQUEST)
    }


    @ExceptionHandler(RegisterNotFoundException::class)
    fun RegisterNotFoundExceptionHandler(
        servletRequest: HttpServletRequest,
        servletResponse: HttpServletResponse,
        exception: RegisterNotFoundException,
    ): ResponseEntity<ErrorMessage> =
        ResponseEntity(ErrorMessage("Registro Nao Localizado", exception.toString()!!), HttpStatus.NOT_FOUND)


    @ExceptionHandler(RegisterConstraintException::class)
    fun RegisterConstraintExceptionHandler(
        servletRequest: HttpServletRequest,
        servletResponse: HttpServletResponse,
        exception: RegisterConstraintException,
    ): ResponseEntity<ErrorMessage> =
        ResponseEntity(ErrorMessage("Violação de Integridade", exception.toString()!!), HttpStatus.METHOD_NOT_ALLOWED)


    @ExceptionHandler(SQLException::class)
    fun SqlExceptionHandler(
        servletRequest: HttpServletRequest,
        servletResponse: HttpServletResponse,
        exception: SQLException,
    ): ResponseEntity<ErrorMessage> {

        println(exception.message)
        // Usado para o MySQL
        if (exception.message!!.contains(chave1, false) && exception.message!!.contains(chave2, false)) {
            val valorDuplicado: String = extraiValorRepetido(exception.message)
            return ResponseEntity(ErrorMessage("Valor duplicado ${valorDuplicado}, o valor já deve existir. Mude o valor e tente novamente.",
                "Valor Duplicado"), HttpStatus.CONFLICT)
        } else
            return ResponseEntity(ErrorMessage("Problema ao executar comandos, chame o adm.", "Outros Erros."),
                HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun MethodArgumentNotValidExceptionHandler(
        servletRequest: HttpServletRequest,
        servletResponse: HttpServletResponse,
        exception: MethodArgumentNotValidException,
    ): ResponseEntity<ErrorMessageValidFieds> {

        val result: BindingResult = exception.getBindingResult()
        val fieldErrors = result.fieldErrors

        var listaCampos = fieldErrors
            .map(
                { it ->
                    ErrorMessageValidField(
                        nomeCampo = it.field,
                        mensagem = it.defaultMessage ?: "",
                        valor = it.rejectedValue.toString()
                    )
                })
            .toList()

        val errorMessageValidFields = ErrorMessageValidFieds("Problemas com dados do formulário.", listaCampos)
        return ResponseEntity(errorMessageValidFields, HttpStatus.BAD_REQUEST)
    }


}