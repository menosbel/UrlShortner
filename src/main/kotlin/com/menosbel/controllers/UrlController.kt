package com.menosbel.controllers

import com.eclipsesource.json.JsonObject
import io.javalin.Javalin
import io.javalin.http.Context

class UrlController(httpServer: Javalin) {

    init {
        httpServer.post("url", ::shortenUrl)
    }

    private fun shortenUrl(ctx: Context) {
        ctx.contentType("application/json")
        ctx.result(JsonObject().toString())
    }
}
