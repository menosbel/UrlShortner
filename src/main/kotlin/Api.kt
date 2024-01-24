import Controllers.HelloWorldController
import Controllers.UrlController
import io.javalin.Javalin

class Api {
    private val httpServer = Javalin.create() { config ->
        config.showJavalinBanner = false
    }

    init {
        registerControllers()
    }

    private fun registerControllers() {
        HelloWorldController(httpServer)
        UrlController(httpServer)
    }

    fun start() {
        httpServer.start()
    }

    fun stop() {
        httpServer.stop()
    }
}