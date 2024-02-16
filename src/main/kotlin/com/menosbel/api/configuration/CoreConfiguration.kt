package com.menosbel.api.configuration

import com.menosbel.core.infrastructure.clock.Clock
import com.menosbel.core.infrastructure.clock.SystemClock

class CoreConfiguration(val clock: Clock = SystemClock())
