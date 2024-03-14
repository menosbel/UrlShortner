package com.menosbel.core.domain

class UrlInfo(url: String, baseUrl: String) {
    val id: Int? = null
    val key = Key()
    val longUrl = url
    val shortUrl = baseUrl + key.value
}