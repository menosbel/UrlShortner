package com.menosbel.core.domain

import com.menosbel.UrlInfoNotFoundError

class UrlInfoInMemoryRepository: UrlInfoRepository {
    private val items = mutableListOf<UrlInfo>()

    override fun findBy(key: String) = items.singleOrNull { it.key.value == key }
        ?: throw UrlInfoNotFoundError("Url not found")

    override fun save(urlInfo: UrlInfo) {
        items.add(urlInfo)
    }
}
