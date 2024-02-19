package com.menosbel

import com.eclipsesource.json.JsonObject
import com.menosbel.api.*
import com.menosbel.api.configuration.ApiConfiguration
import com.menosbel.api.configuration.CoreConfiguration
import com.menosbel.core.infrastructure.InMemoryRepositoryProvider
import com.menosbel.core.infrastructure.UseCaseProvider
import com.menosbel.lang.formatAsISO8601
import io.restassured.RestAssured
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers
import org.junit.jupiter.api.*
import java.time.LocalDateTime

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApiE2ETests {

    private val NOW = LocalDateTime.now()
    private val stoppedClock = StoppedClock.at(NOW)
    private val coreConfiguration = CoreConfiguration(stoppedClock)
    private val port = 8080
    private val baseUrl = "http://localhost/"
    private val repositoryProvider = InMemoryRepositoryProvider()
    private val useCaseProvider = UseCaseProvider(coreConfiguration, baseUrl, repositoryProvider)
    private val apiConfiguration = ApiConfiguration(useCaseProvider, port)
    private val api = Api(apiConfiguration)

    @BeforeAll
    fun setUp() {
        RestAssured.port = port
        RestAssured.baseURI = baseUrl
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
    fun `should shorten url`() {
        val url = "https://www.google.com"
        val body = JsonObject().add("url", url).toString()

        Given {
            body(body)
        } When {
            post("/url")
        } Then {
            statusCode(200)
            body("longUrl", equalTo(url))
            body("shortUrl", containsString(RestAssured.baseURI))
            body("key", Matchers.hasLength(8))
            body("createdAt", equalTo(NOW.formatAsISO8601()))
        }

    }
}