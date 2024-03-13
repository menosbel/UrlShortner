package com.menosbel.core.infrastructure.jooq

import com.menosbel.core.domain.UrlInfo
import com.menosbel.core.domain.UrlInfoRepository
import com.menosbel.core.infrastructure.Credentials

class JooqUrlInfo(private val credentials: Credentials) : UrlInfoRepository {
    override fun findBy(key: String): UrlInfo {
        TODO("Not yet implemented")
    }

    override fun save(url: UrlInfo) {
        TODO("Not yet implemented")
    }

}
