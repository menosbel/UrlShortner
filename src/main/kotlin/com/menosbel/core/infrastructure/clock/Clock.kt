package com.menosbel.core.infrastructure.clock

import java.time.LocalDateTime

interface Clock {
    fun now(): LocalDateTime
}