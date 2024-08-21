package com.menosbel.api

import com.menosbel.api.configuration.AppConfiguration
import com.menosbel.core.infrastructure.JdbcCredentials
import com.menosbel.core.infrastructure.JdbcUrl
import com.menosbel.core.infrastructure.UseCaseProvider
import com.menosbel.core.infrastructure.jooq.JooqRepositoryProvider
import com.menosbel.utils.Env
import org.flywaydb.core.Flyway;

fun main() {
    val env = Env()
    val port = (env.getVariable("PORT") ?: "8080").toInt()
    val baseUrl = buildBaseUrl(env, port)

    val jdbcUrl = buildJdbcUrl(env)
    val repositoryProvider = buildJooqRepositoryProvider(env, jdbcUrl)
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

private fun buildJooqRepositoryProvider(env: Env, jdbcUrl: JdbcUrl): JooqRepositoryProvider {
    val dbUser = env.getVariableOrThrow("DB_USER")
    val dbPassword = env.getVariableOrThrow("DB_PASSWORD")
    val jdbcCredentials = JdbcCredentials(jdbcUrl, dbUser, dbPassword)
    return JooqRepositoryProvider(jdbcCredentials)
}

private fun buildJdbcUrl(env: Env): JdbcUrl {
    val dbDriver = env.getVariableOrThrow("DB_DRIVER")
    val dbHost = env.getVariableOrThrow("DB_HOST")
    val dbPort = env.getVariableOrThrow("DB_PORT").toInt()
    val dbName = env.getVariableOrThrow("DB_NAME")
    return JdbcUrl(dbDriver, dbHost, dbPort, dbName)
}

private fun buildBaseUrl(env: Env, port: Int) = env.getVariable("BASE_URL") ?: "http://localhost:${port}/"

