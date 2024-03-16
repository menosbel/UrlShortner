package com.menosbel.core.infrastructure

data class JdbcUrl(private val driver: String, private val host: String, private val port: Int, private val name: String) {
    override fun toString() = "jdbc:$driver://$host:$port/$name"
}