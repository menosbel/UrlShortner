package com.menosbel.core.domain

class UrlInfoInMemoryRepository: UrlInfoRepository {
    private val items = mutableListOf<UrlInfo>()

    override fun findBy(key: String) = items.single { it.key.value == key }

    override fun save(urlInfo: UrlInfo) {
        items.add(urlInfo)
    }
}
