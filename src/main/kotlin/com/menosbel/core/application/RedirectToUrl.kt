package com.menosbel.core.application

import com.menosbel.core.domain.UrlInfoRepository

class RedirectToUrl {

    fun exec(key: String, repository: UrlInfoRepository): Response {
        val urlInfo = repository.findBy(key)

        return Response(urlInfo.longUrl)

    }

    data class Response(val longUrl: String)
}