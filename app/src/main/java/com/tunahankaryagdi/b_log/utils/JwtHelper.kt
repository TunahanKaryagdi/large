package com.tunahankaryagdi.b_log.utils

import com.auth0.android.jwt.JWT
import java.util.Date

object JwtHelper {

    fun decodeAndGetId(token: String): String? {
        val jwt = JWT(token)
        return jwt.getClaim("id").asString()
    }

    fun isTokenValid(token: String) : Boolean{
        val expDate = JWT(token).expiresAt
        val currentDate = Date()
        if (currentDate.before(expDate)){
            return true
        }
        return false
    }
}