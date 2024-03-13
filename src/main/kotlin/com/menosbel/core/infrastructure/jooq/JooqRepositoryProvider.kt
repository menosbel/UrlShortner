package com.menosbel.core.infrastructure.jooq

import com.menosbel.core.infrastructure.Credentials
import com.menosbel.core.infrastructure.RepositoryProvider

class JooqRepositoryProvider(credentials: Credentials): RepositoryProvider {
    private val urlInfoRepository = JooqUrlInfo(credentials)

    override fun urlInfo() = urlInfoRepository
}
