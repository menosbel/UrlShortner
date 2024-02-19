package com.menosbel.core.infrastructure

import com.menosbel.core.domain.UrlInfoRepository

interface RepositoryProvider {
    fun urlInfo(): UrlInfoRepository
}
