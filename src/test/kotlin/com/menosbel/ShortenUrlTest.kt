package com.menosbel

import com.menosbel.core.application.ShortenUrl
import com.menosbel.core.infrastructure.UrlInfoInMemoryRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ShortenUrlTest {
    private val baseUrl = "https://someurl.com/"
    private val repository= UrlInfoInMemoryRepository()
    private val shortenUrl = ShortenUrl(baseUrl, repository)

    @Test
    fun `should return shortened url info`() {
        val url = "https://www.google.com"
        val keyLength = 8

        val response = shortenUrl.exec(url)

        assertThat(response.longUrl).isEqualTo(url)
        assertThat(response.shortUrl).contains(baseUrl)
        assertThat(response.key.length).isEqualTo(keyLength)
    }

    @Test
    fun `should save URL info`() {
        val url = "https://www.google.com"
        val keyLength = 8

        val response = shortenUrl.exec(url)
        val urlInfo = repository.findBy(response.key)

        assertThat(urlInfo.longUrl).isEqualTo(url)
        assertThat(urlInfo.shortUrl).contains(baseUrl)
        assertThat(urlInfo.key.value.length).isEqualTo(keyLength)
    }


}