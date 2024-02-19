package com.menosbel.core.domain

interface UrlInfoRepository {
    fun findBy(key: String): UrlInfo
    fun save(url: UrlInfo)
}