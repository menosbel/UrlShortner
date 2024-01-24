package Controllers

import io.javalin.Javalin
import io.javalin.http.Context

class HelloWorldController(httpServer: Javalin) {

    init {
        httpServer.get("/", ::greet)
    }

    private fun greet(ctx: Context) {
        ctx.result("Hello World")
    }

}
