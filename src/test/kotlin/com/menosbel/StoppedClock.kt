package com.menosbel

import com.menosbel.core.infrastructure.clock.Clock
import java.time.LocalDateTime

class StoppedClock private constructor (private val now: LocalDateTime): Clock {
    override fun now() = now

    companion object {
        fun at(now: LocalDateTime) = StoppedClock(now)
    }
}
