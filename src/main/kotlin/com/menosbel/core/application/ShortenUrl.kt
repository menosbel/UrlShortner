package com.menosbel.core.application

import com.menosbel.lang.formatAsISO8601
import com.menosbel.core.infrastructure.clock.Clock
import com.menosbel.core.domain.Key

class ShortenUrl(private val clock: Clock, private val baseUrl: String) {

    fun exec(url: String): Response {
        val key = Key()
        val shortUrl = baseUrl + key.value

        return Response(url, shortUrl, key.value, clock.now().formatAsISO8601())
    }

    data class Response(val longUrl: String, val shortUrl: String, val key: String, val createdAt: String) {}
}