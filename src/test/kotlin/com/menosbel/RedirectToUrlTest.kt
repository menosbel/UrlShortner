package com.menosbel

import com.menosbel.core.application.RedirectToUrl
import com.menosbel.core.domain.UrlInfo
import com.menosbel.core.domain.UrlInfoInMemoryRepository
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class RedirectToUrlTest {
    private val redirectToUrl = RedirectToUrl()
    private val repository = UrlInfoInMemoryRepository()
    private val baseUrl = "https://someurl.com/"

    @Test
    fun `should return long url`() {
        val url = "https://www.google.com"
        val urlInfo = UrlInfo(url, baseUrl)
        repository.save(urlInfo)

        val response = redirectToUrl.exec(urlInfo.key.value, repository)

        assertThat(response.longUrl).isEqualTo(url)
    }
}