package com.menosbel.core.infrastructure.jooq

import com.menosbel.core.infrastructure.JdbcCredentials
import com.menosbel.core.infrastructure.RepositoryProvider

class JooqRepositoryProvider(jdbcCredentials: JdbcCredentials): RepositoryProvider {
    private val urlInfoRepository = JooqUrlInfo(jdbcCredentials)

    override fun urlInfo() = urlInfoRepository
}
