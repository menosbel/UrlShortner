package com.menosbel.api.extensions

import com.eclipsesource.json.Json
import com.eclipsesource.json.JsonObject
import io.javalin.http.Context

fun Context.requestAsJsonObject(): JsonObject = Json.parse(this.body()).asObject()
