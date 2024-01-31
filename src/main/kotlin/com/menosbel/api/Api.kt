package com.menosbel.api

import com.menosbel.controllers.UrlController
import io.javalin.Javalin

class Api {
    private val httpServer = Javalin.create() { config ->
        config.showJavalinBanner = false
    }

    init {
        registerControllers()
    }

    private fun registerControllers() {
        UrlController(httpServer)
    }

    fun start() {
        httpServer.start()
    }

    fun stop() {
        httpServer.stop()
    }
}