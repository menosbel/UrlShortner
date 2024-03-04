package com.menosbel.api

import com.menosbel.api.configuration.ApiConfiguration
import com.menosbel.controllers.UrlController
import io.javalin.Javalin

class Api(private val config: ApiConfiguration) {
    private val httpServer = Javalin.create() { config ->
        config.showJavalinBanner = false
    }

    init {
        registerControllers()
    }

    private fun registerControllers() {
        UrlController(httpServer, config.useCaseProvider)
    }

    fun start() {
        httpServer.start(config.port)
    }

    fun stop() {
        httpServer.close()
    }
}