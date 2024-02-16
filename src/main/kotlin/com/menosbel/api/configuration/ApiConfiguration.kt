package com.menosbel.api.configuration

import com.menosbel.core.infrastructure.UseCaseProvider

class ApiConfiguration(
    val useCaseProvider: UseCaseProvider,
    val port: Int = 80,
) {

}
