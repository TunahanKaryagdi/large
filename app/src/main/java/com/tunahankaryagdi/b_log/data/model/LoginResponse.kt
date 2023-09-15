package com.tunahankaryagdi.b_log.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val message: String,
    val data: LoginDto?,
)


data class LoginDto(

    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("refresh_token")
    val refreshToken: String
)