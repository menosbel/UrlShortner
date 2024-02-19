package com.menosbel.core.infrastructure

import com.menosbel.core.domain.UrlInfoInMemoryRepository
import com.menosbel.core.domain.UrlInfoRepository

class InMemoryRepositoryProvider: RepositoryProvider {
    override fun urlInfo(): UrlInfoRepository {
        return UrlInfoInMemoryRepository()
    }
}