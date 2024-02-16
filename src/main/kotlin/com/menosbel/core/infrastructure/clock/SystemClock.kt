package com.menosbel.core.infrastructure.clock

import java.time.LocalDateTime

class SystemClock: Clock {
    override fun now(): LocalDateTime = LocalDateTime.now()

}
