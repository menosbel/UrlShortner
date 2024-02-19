package com.menosbel.core.domain

class UrlInfo(url: String, baseUrl: String) {
    val key = Key()
    val longUrl = url
    val shortUrl = baseUrl + key.value
}