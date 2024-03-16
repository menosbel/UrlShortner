package com.menosbel.api

import com.menosbel.api.configuration.AppConfiguration
import com.menosbel.core.infrastructure.JdbcCredentials
import com.menosbel.core.infrastructure.JdbcUrl
import com.menosbel.core.infrastructure.UseCaseProvider
import com.menosbel.core.infrastructure.jooq.JooqRepositoryProvider
import io.github.cdimascio.dotenv.dotenv

fun main() {
    val dotenv = dotenv()

    val PORT = (dotenv["PORT"] ?: "8080").toInt()
    val baseUrl = dotenv["BASE_URL"] ?: "http://localhost:${PORT}/"

    val jdbcUrl = JdbcUrl(dotenv["DB_DRIVER"], dotenv["DB_HOST"], dotenv["DB_PORT"].toInt(), dotenv["DB_NAME"])
    val jdbcCredentials = JdbcCredentials(jdbcUrl, dotenv["DB_USER"]!!, dotenv["DB_PASSWORD"]!!)
    val repositoryProvider = JooqRepositoryProvider(jdbcCredentials)
    val useCaseProvider = UseCaseProvider(baseUrl, repositoryProvider)

    val app = App(AppConfiguration(useCaseProvider, PORT))

    app.start()
}
