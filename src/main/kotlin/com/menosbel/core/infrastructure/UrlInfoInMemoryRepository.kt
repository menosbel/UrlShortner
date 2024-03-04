package com.menosbel.core.infrastructure

import com.menosbel.core.domain.UrlInfo
import com.menosbel.core.domain.UrlInfoNotFoundError
import com.menosbel.core.domain.UrlInfoRepository

class UrlInfoInMemoryRepository: UrlInfoRepository {
    private val items = mutableListOf<UrlInfo>()

    override fun findBy(key: String) = items.singleOrNull { it.key.value == key }
        ?: throw UrlInfoNotFoundError("Url not found")

    override fun save(urlInfo: UrlInfo) {
        items.add(urlInfo)
    }
}
