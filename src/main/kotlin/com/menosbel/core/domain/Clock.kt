package com.menosbel.core.domain

import java.time.LocalDateTime

interface Clock {
    fun now(): LocalDateTime
}