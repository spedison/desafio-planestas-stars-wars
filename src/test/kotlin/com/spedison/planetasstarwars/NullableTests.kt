package com.spedison.planetasstarwars

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*


internal class NullableTests {
    @BeforeEach
    fun setUp() {

    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun testaExceptioSeNullo() {

        val val1: Long = 10L
        val val2: Long? = null

        val a1: Optional<Long> = Optional.of(val1)
        val a2: Optional<Long> = Optional.ofNullable(val2)

        println("A1")
        a1.ifPresent ( ::println )
        println("A2")
        a2.ifPresent ( ::println )

        println("A1")
        a1.ifPresentOrElse(::println, {
            println("A1 Vazio")
        })

        println("A2")
        a2.ifPresentOrElse(::println, {
            println("A2 Vazio")
        })

        Assertions.assertEquals(a1.get(), val1)
        Assertions.assertEquals(a2.get(), val2)

    }
}