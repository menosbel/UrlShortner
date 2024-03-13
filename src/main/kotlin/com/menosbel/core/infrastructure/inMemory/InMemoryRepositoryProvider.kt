package com.menosbel.core.infrastructure.inMemory

import com.menosbel.core.infrastructure.RepositoryProvider

class InMemoryRepositoryProvider: RepositoryProvider {
    private val urlInfoRepository = UrlInfoInMemoryRepository()

    override fun urlInfo() = urlInfoRepository
}