package com.menosbel.utils

import io.github.cdimascio.dotenv.dotenv

class Env {
    private val dotenv = dotenv()

    fun getVariableOrThrow(key: String): String {
        val variable = getVariable(key)
        if (variable.isNullOrEmpty()) throw Exception("Variable de entorno $key no configurada")
        else return variable
    }

    fun getVariable(key: String): String? = System.getenv(key) ?: dotenv[key]
}