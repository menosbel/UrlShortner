package com.menosbel.core.application

import com.menosbel.core.domain.UrlInfo
import com.menosbel.core.domain.UrlInfoRepository
import com.menosbel.core.infrastructure.clock.Clock

class ShortenUrl(private val clock: Clock, private val baseUrl: String, private val repository: UrlInfoRepository) {

    fun exec(url: String): Response {
        val urlInfo = UrlInfo(url, baseUrl)
        repository.save(urlInfo)

        return Response(url, urlInfo.shortUrl, urlInfo.key.value)
    }

    data class Response(val longUrl: String, val shortUrl: String, val key: String) {}
}