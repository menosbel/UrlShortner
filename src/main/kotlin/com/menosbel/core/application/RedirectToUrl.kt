package com.menosbel.core.application

import com.menosbel.core.domain.UrlInfoRepository

class RedirectToUrl(private val repository: UrlInfoRepository) {

    fun exec(key: String): Response {
        val urlInfo = repository.findBy(key)

        return Response(urlInfo.longUrl)
    }

    data class Response(val longUrl: String)
}