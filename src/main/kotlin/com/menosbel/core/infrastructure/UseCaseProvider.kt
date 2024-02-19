package com.menosbel.core.infrastructure

import com.menosbel.api.configuration.CoreConfiguration
import com.menosbel.core.application.ShortenUrl

class UseCaseProvider(private val config: CoreConfiguration, private val baseUrl: String, private val repositoryProvider: RepositoryProvider) {
    fun shortenUrl() = ShortenUrl(baseUrl, repositoryProvider.urlInfo())
}
