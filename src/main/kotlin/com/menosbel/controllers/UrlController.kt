package com.menosbel.controllers

import com.menosbel.api.extensions.getParamAsString
import com.menosbel.api.extensions.requestAsJsonObject
import com.menosbel.core.domain.UrlInfoNotFoundError
import com.menosbel.core.infrastructure.UseCaseProvider
import io.javalin.Javalin
import io.javalin.http.Context
import io.javalin.http.HttpStatus

class UrlController(httpServer: Javalin, private val useCaseProvider: UseCaseProvider) {

    init {
        httpServer.post("url", ::shortenUrl)
        httpServer.get("{key}", ::redirectToUrl)
        urlInfoNotFoundError(httpServer)
    }

    private fun urlInfoNotFoundError(httpServer: Javalin) {
        httpServer.exception(UrlInfoNotFoundError::class.java) { e, ctx ->
            ctx.status(404)
        }.error(404) { ctx ->
            ctx.result("Url not found")
        }
    }

    private fun shortenUrl(ctx: Context) {
        val jsonBody = ctx.requestAsJsonObject()
        val url = jsonBody.getParamAsString("url")
        val response = useCaseProvider.shortenUrl().exec(url)
        ctx.json(response)
    }

    private fun redirectToUrl(ctx: Context) {
        val key = ctx.pathParam("key")
        val response = useCaseProvider.redirectToUrl().exec(key)
        ctx.redirect(response.longUrl, HttpStatus.PERMANENT_REDIRECT)
    }
}
