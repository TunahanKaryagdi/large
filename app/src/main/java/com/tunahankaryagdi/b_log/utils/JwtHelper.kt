package com.tunahankaryagdi.b_log.utils

import com.auth0.android.jwt.JWT

object JwtHelper {

    fun decode(token: String): String? {
        val jwt = JWT(token)
        return jwt.getClaim("id").asString()
    }
}