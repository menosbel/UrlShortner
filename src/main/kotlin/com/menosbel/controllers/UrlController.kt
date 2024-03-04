package com.menosbel.controllers

import com.menosbel.api.extensions.getParamAsString
import com.menosbel.api.extensions.requestAsJsonObject
import com.menosbel.core.infrastructure.UseCaseProvider
import io.javalin.Javalin
import io.javalin.http.Context
import io.javalin.http.HttpStatus
import io.javalin.http.NotFoundResponse

class UrlController(httpServer: Javalin, private val useCaseProvider: UseCaseProvider) {

    init {
        httpServer.post("url", ::shortenUrl)
        httpServer.get("{key}", ::redirectToUrl)
    }

    private fun shortenUrl(ctx: Context) {
        val jsonBody = ctx.requestAsJsonObject()
        val url = jsonBody.getParamAsString("url")
        val response = useCaseProvider.shortenUrl().exec(url)
        ctx.json(response)
    }

    private fun redirectToUrl(ctx: Context) {
        val key = ctx.pathParam("key")

        try {
            val response = useCaseProvider.redirectToUrl().exec(key)
            //ctx.header(Header.LOCATION, response.longUrl).status(302)
            println(response.longUrl)
            ctx.redirect(response.longUrl, HttpStatus.PERMANENT_REDIRECT)
        } catch (e: Exception) {
            throw NotFoundResponse(e.message!!)
        }

    }
}
