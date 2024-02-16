package com.menosbel.core.infrastructure

import com.menosbel.api.configuration.CoreConfiguration
import com.menosbel.core.application.ShortenUrl

class UseCaseProvider(private val config: CoreConfiguration, private val baseUrl: String) {
    fun shortenUrl() = ShortenUrl(config.clock, baseUrl)
}
