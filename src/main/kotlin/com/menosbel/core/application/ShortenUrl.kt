package com.menosbel.core.application

import com.menosbel.core.domain.Clock
import com.menosbel.core.domain.Key
import java.time.LocalDateTime

class ShortenUrl(private val clock: Clock, private val baseUrl: String) {

    fun exec(url: String): Response {
        val key = Key()
        val shortUrl = baseUrl + key

        return Response(url, shortUrl, key.value, clock.now())
    }

    data class Response(val longUrl: String, val shortUrl: String, val key: String, val createdAt: LocalDateTime) {}
}