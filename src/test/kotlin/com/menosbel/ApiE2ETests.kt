package com.menosbel

import com.menosbel.api.Api
import com.eclipsesource.json.JsonObject
import io.restassured.RestAssured
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApiE2ETests {

    private val api = Api()

    @BeforeAll
    fun setUp() {
        val port = System.getProperty("server.port")
        if (port == null) {
            RestAssured.port = Integer.valueOf(8080)
        } else {
            RestAssured.port = Integer.valueOf(port)
        }

        var baseHost = System.getProperty("server.host")
        if (baseHost == null) {
            baseHost = "http://localhost"
        }
        RestAssured.baseURI = baseHost
    }

    @BeforeEach
    fun startApi() {
        api.start()
    }

    @AfterEach
    fun stopApi() {
        api.stop()
    }

    @Test
    @Disabled
    fun `should shorten url`() {
        val url = "https://www.google.com"
        val body = JsonObject().add("url", url).toString()

        Given {
            body(body)
        } When {
            post("/url")
        } Then {
            statusCode(200)
            body("long_url", equalTo(url))
            body("short_url", containsString(RestAssured.baseURI))
            body("key", Matchers.hasLength(8))
        }

    }
}