package com.tunahankaryagdi.b_log.data.model

data class LoginResponse(
    val message: String,
    val data: Data?,
)


data class Data(

    val id: String,
    val accessToken: String,
    val refreshToken: String
)