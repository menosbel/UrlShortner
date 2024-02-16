package com.menosbel.api.extensions

import com.eclipsesource.json.JsonObject

fun JsonObject.getParamAsString(param: String) = this[param].asString()
