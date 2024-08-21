package com.menosbel.domain

import com.menosbel.core.domain.Key
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class KeyTest {
    @Test
    fun `should be 8 characters long`() {
        val keyLength = 8

        val randoms = List(10_000) { Key() }

        assertTrue { randoms.all { it.value.length == 7 }}
    }

    @Test
    fun `should contain letters and or numbers`() {
        val randoms = List(10_000) { Key() }

        assertTrue { randoms.all { it.value.matches(Regex("^[0-9a-zA-Z]+$")) }}

    }
}