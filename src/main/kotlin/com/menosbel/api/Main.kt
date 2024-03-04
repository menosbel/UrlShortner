package com.menosbel.api

import com.menosbel.api.configuration.ApiConfiguration
import com.menosbel.core.infrastructure.InMemoryRepositoryProvider
import com.menosbel.core.infrastructure.UseCaseProvider
import io.github.cdimascio.dotenv.dotenv

fun main() {
    val dotenv = dotenv()
    val port = (dotenv["PORT"] ?: "8080").toInt()
    val baseUrl = dotenv["BASE_URL"] ?: "http://localhost:8080/"
    val repositoryProvider = InMemoryRepositoryProvider()
    val useCaseProvider = UseCaseProvider(baseUrl, repositoryProvider)
    val api = Api(ApiConfiguration(useCaseProvider, port))
    api.start()
}
