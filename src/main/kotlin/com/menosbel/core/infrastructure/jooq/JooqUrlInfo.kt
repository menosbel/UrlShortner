package com.menosbel.core.infrastructure.jooq

import com.menosbel.UrlShortner.infrastructure.db.jooq.generated.Tables.URL_INFO
import com.menosbel.core.domain.UrlInfo
import com.menosbel.core.domain.UrlInfoNotFoundError
import com.menosbel.core.domain.UrlInfoRepository
import com.menosbel.core.infrastructure.Credentials
import org.jooq.impl.DSL

class JooqUrlInfo(private val credentials: Credentials) : UrlInfoRepository {
    private fun context() = DSL.using(credentials.url, credentials.user, credentials.password)

    override fun findBy(key: String): UrlInfo {
        return context()
            .selectFrom(URL_INFO)
            .where(URL_INFO.KEY_URL.eq(key.uppercase()))
            .fetchOneInto(UrlInfo::class.java) ?: throw UrlInfoNotFoundError("Url not found")
    }

    override fun save(url: UrlInfo) {
        context()
            .insertInto(URL_INFO, URL_INFO.LONG_URL, URL_INFO.SHORT_URL, URL_INFO.KEY_URL)
            .values(url.longUrl, url.shortUrl, url.key.value)
            .execute()
    }


}
