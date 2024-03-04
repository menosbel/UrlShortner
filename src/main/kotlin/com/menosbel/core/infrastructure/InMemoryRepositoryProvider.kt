package com.menosbel.core.infrastructure

import com.menosbel.core.domain.UrlInfoRepository

class InMemoryRepositoryProvider: RepositoryProvider {
    private val urlInfoRepository = UrlInfoInMemoryRepository()

    override fun urlInfo(): UrlInfoRepository {
        return urlInfoRepository
    }
}