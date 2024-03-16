package com.menosbel.api.configuration

import com.menosbel.core.infrastructure.UseCaseProvider

class AppConfiguration(
    val useCaseProvider: UseCaseProvider,
    val port: Int = 80,
) {

}
