package com.menosbel.controllers

import com.menosbel.core.infrastructure.UseCaseProvider
import com.menosbel.api.extensions.getParamAsString
import com.menosbel.api.extensions.requestAsJsonObject
import io.javalin.Javalin
import io.javalin.http.Context

class UrlController(private val httpServer: Javalin, private val useCaseProvider: UseCaseProvider) {

    init {
        httpServer.post("url", ::shortenUrl)
    }

    private fun shortenUrl(ctx: Context) {
        val jsonBody = ctx.requestAsJsonObject()
        val url = jsonBody.getParamAsString("url")
        val response = useCaseProvider.shortenUrl().exec(url)
        ctx.json(response)
    }
}
