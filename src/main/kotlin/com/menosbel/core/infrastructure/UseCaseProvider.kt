package com.menosbel.core.infrastructure

import com.menosbel.core.application.RedirectToUrl
import com.menosbel.core.application.ShortenUrl

class UseCaseProvider(
    private val baseUrl: String,
    private val repositoryProvider: RepositoryProvider
) {
    fun shortenUrl() = ShortenUrl(baseUrl, repositoryProvider.urlInfo())

    fun redirectToUrl() = RedirectToUrl(repositoryProvider.urlInfo())
}
