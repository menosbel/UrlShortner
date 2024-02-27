package com.menosbel

import com.menosbel.core.application.RedirectToUrl
import com.menosbel.core.domain.UrlInfo
import com.menosbel.core.domain.UrlInfoInMemoryRepository
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows

class RedirectToUrlTest {
    private val repository = UrlInfoInMemoryRepository()
    private val redirectToUrl = RedirectToUrl(repository)
    private val baseUrl = "https://someurl.com/"

    @Test
    fun `should return long url`() {
        val url = "https://www.google.com"
        val urlInfo = UrlInfo(url, baseUrl)
        repository.save(urlInfo)

        val response = redirectToUrl.exec(urlInfo.key.value)

        assertThat(response.longUrl).isEqualTo(url)
    }

    @Test
    fun `should fail if key does not exists`() {
        val someKey = "lOka8Hys"

        assertThrows<UrlInfoNotFoundError> {
            redirectToUrl.exec(someKey)
        }
    }
}