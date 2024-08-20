package com.menosbel

import com.eclipsesource.json.JsonObject
import com.menosbel.api.*
import com.menosbel.api.configuration.AppConfiguration
import com.menosbel.core.domain.UrlInfo
import com.menosbel.core.infrastructure.JdbcCredentials
import com.menosbel.core.infrastructure.JdbcUrl
import com.menosbel.core.infrastructure.UseCaseProvider
import com.menosbel.core.infrastructure.jooq.JooqRepositoryProvider
import io.github.cdimascio.dotenv.dotenv
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
@Tag("slow")
class AppE2ETests {
    private val PORT = 8080
    private val baseUrl = "http://localhost/"
    private val dotenv = dotenv()
    private val jdbcUrl = JdbcUrl(dotenv["TEST_DB_DRIVER"], dotenv["TEST_DB_HOST"], dotenv["TEST_DB_PORT"].toInt(), dotenv["TEST_DB_NAME"])
    private val jdbcCredentials = JdbcCredentials(jdbcUrl, dotenv["TEST_DB_USER"]!!, dotenv["TEST_DB_PASSWORD"]!!)
    private val repositoryProvider = JooqRepositoryProvider(jdbcCredentials)
    private val useCaseProvider = UseCaseProvider(baseUrl, repositoryProvider)
    private val appConfiguration = AppConfiguration(useCaseProvider, PORT)

    private lateinit var app: App

    @BeforeAll
    fun setUp() {
        RestAssured.port = PORT
        RestAssured.baseURI = baseUrl
        RestAssured.config.redirect(RedirectConfig().followRedirects(false))
    }

    @BeforeEach
    fun startApp() {
        app = App(appConfiguration)
        app.start()
    }

    @AfterEach
    fun stopApp() {
        app.stop()
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
    fun `should redirect user to long url`() {
        val url = "https://www.google.com"
        val urlInfo = UrlInfo(url, baseUrl)
        repositoryProvider.urlInfo().save(urlInfo)

        Given {
            redirects().max(0).and().redirects().follow(false)
        }
        When {
            get("/${urlInfo.key.value}")
        } Then {
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