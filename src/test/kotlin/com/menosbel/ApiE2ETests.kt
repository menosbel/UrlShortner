package com.menosbel

import com.eclipsesource.json.JsonObject
import com.menosbel.api.*
import com.menosbel.api.configuration.ApiConfiguration
import com.menosbel.core.domain.UrlInfo
import com.menosbel.core.infrastructure.InMemoryRepositoryProvider
import com.menosbel.core.infrastructure.UseCaseProvider
import io.restassured.RestAssured
import io.restassured.config.RedirectConfig
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.parsing.Parser
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApiE2ETests {

    private val port = 8080
    private val baseUrl = "http://localhost/"
    private val repositoryProvider = InMemoryRepositoryProvider()
    private val urlInfoInMemoryRepository = repositoryProvider.urlInfo()
    private val useCaseProvider = UseCaseProvider(baseUrl, repositoryProvider)
    private val apiConfiguration = ApiConfiguration(useCaseProvider, port)
    private lateinit var api: Api

    @BeforeAll
    fun setUp() {
        RestAssured.port = port
        RestAssured.baseURI = baseUrl
        RestAssured.config.redirect(RedirectConfig().followRedirects(false))
    }

    @BeforeEach
    fun startApi() {
        api = Api(apiConfiguration)
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
        }
    }

    @Test
    @Disabled
    fun `should redirect user to long url`() {
        val url = "https://www.google.com"
        val urlInfo = UrlInfo(url, baseUrl)
        urlInfoInMemoryRepository.save(urlInfo)

        Given {
            redirects().max(0).and().redirects().follow(false)
        }
        When {
            get("/${urlInfo.key.value}")
        } Then {
            val headers = extract().response().headers
            val statusCode = extract().response().statusCode
            statusCode(302)
        }
    }

    @Test
    fun `should fail if key is not found`() {
        val someKey = "kYhNk6aH"

            RestAssured.registerParser("text/plain", Parser.TEXT)

        When {
            get("/${someKey}")
        } Then {
            statusCode(404)
            body(equalTo("Url not found"))
        }
    }
}