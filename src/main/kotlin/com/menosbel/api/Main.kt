package com.menosbel.api

import com.menosbel.api.configuration.ApiConfiguration
import com.menosbel.api.configuration.CoreConfiguration
import com.menosbel.core.infrastructure.UseCaseProvider
import io.github.cdimascio.dotenv.dotenv

fun main() {
    val dotenv = dotenv()
    val port = (dotenv["PORT"] ?: "8080").toInt()
    val baseUrl = dotenv["BASE_URL"] ?: "http://localhost/"
    val useCaseProvider = UseCaseProvider(CoreConfiguration(), baseUrl)
    val api = Api(
        ApiConfiguration(
        useCaseProvider,
        port
    )
    )
    api.start()
}
