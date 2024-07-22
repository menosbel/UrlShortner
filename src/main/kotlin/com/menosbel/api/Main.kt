package com.menosbel.api

import com.menosbel.api.configuration.AppConfiguration
import com.menosbel.core.infrastructure.JdbcCredentials
import com.menosbel.core.infrastructure.JdbcUrl
import com.menosbel.core.infrastructure.UseCaseProvider
import com.menosbel.core.infrastructure.jooq.JooqRepositoryProvider
import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv
import org.flywaydb.core.Flyway;

fun main() {
    val dotenv = dotenv()
    val port = (dotenv["PORT"] ?: "8080").toInt()
    val baseUrl = buildBaseUrl(dotenv, port)

    val jdbcUrl = buildJdbcUrl(dotenv)
    val repositoryProvider = buildJooqRepositoryProvider(dotenv, jdbcUrl)
    val useCaseProvider = UseCaseProvider(baseUrl, repositoryProvider)

    val app = App(AppConfiguration(useCaseProvider, port))
    runFlywayMigrations(jdbcUrl)

    app.start()
}

private fun runFlywayMigrations(jdbcUrl: JdbcUrl) {
    val root = System.getProperty("user.dir")
    val flyway = Flyway
        .configure()
        .dataSource(jdbcUrl.toString(), "postgres", "1234")
        .locations("filesystem:${root}/src/main/resources/db")
        .load()
    flyway.migrate()
}

private fun buildJooqRepositoryProvider(dotenv: Dotenv, jdbcUrl: JdbcUrl): JooqRepositoryProvider {
    val dbUser = dotenv["DB_USER"]!!
    val dbPassword = dotenv["DB_PASSWORD"]!!
    val jdbcCredentials = JdbcCredentials(jdbcUrl, dbUser, dbPassword)
    return JooqRepositoryProvider(jdbcCredentials)
}

private fun buildJdbcUrl(dotenv: Dotenv): JdbcUrl {
    val dbDriver = dotenv["DB_DRIVER"]
    val dbHost = dotenv["DB_HOST"]
    val dbPort = dotenv["DB_PORT"].toInt()
    val dbName = dotenv["DB_NAME"]
    return JdbcUrl(dbDriver, dbHost, dbPort, dbName)
}

private fun buildBaseUrl(dotenv: Dotenv, port: Int) = dotenv["BASE_URL"] ?: "http://localhost:${port}/"

