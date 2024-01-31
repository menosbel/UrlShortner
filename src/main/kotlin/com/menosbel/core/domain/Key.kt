package com.menosbel.core.domain

class Key {
    var value: String = ""
        private set
    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    private val SIZE = 8

    init {
        value = generateKey()
    }

    private fun generateKey(): String {
        return List(SIZE) { charPool.random() }.joinToString("")
    }
}