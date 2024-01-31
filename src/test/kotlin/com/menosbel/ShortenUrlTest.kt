package com.menosbel

import com.menosbel.core.application.ShortenUrl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ShortenUrlTest {
    private val baseUrl = "https://someurl.com/"
    private val NOW = LocalDateTime.of(2021, 8, 18, 17, 35, 15)
    private val clock = StoppedClock.at(NOW)
    private val shortenUrl = ShortenUrl(clock, baseUrl)

    @Test
    fun `should return shortened url info`() {
        val url = "https://www.google.com"
        val keyLength = 8

        val response = shortenUrl.exec(url)

        assertThat(response.longUrl).isEqualTo(url)
        assertThat(response.shortUrl).contains(baseUrl)
        assertThat(response.key.length).isEqualTo(keyLength)
        assertThat(response.createdAt).isEqualTo(NOW)
    }


}