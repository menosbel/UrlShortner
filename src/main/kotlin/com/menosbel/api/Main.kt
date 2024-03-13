package com.menosbel.api

import com.menosbel.api.configuration.ApiConfiguration
import com.menosbel.core.infrastructure.Credentials
import com.menosbel.core.infrastructure.UseCaseProvider
import com.menosbel.core.infrastructure.jooq.JooqRepositoryProvider
import io.github.cdimascio.dotenv.dotenv

fun main() {
    val dotenv = dotenv()
    val PORT = (dotenv["PORT"] ?: "8080").toInt()
    val baseUrl = dotenv["BASE_URL"] ?: "http://localhost:${PORT}/"
    val credentials = Credentials(dotenv["DB_URL"]!!, dotenv["DB_USER"]!!, dotenv["DB_PASSWORD"]!!)
    val repositoryProvider = JooqRepositoryProvider(credentials)
    val useCaseProvider = UseCaseProvider(baseUrl, repositoryProvider)
    val api = Api(ApiConfiguration(useCaseProvider, PORT))
    api.start()
}
