package com.menosbel.core.infrastructure

data class JdbcCredentials(val url: JdbcUrl, val user: String, val password: String)
