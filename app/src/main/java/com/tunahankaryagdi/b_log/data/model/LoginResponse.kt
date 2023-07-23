package com.tunahankaryagdi.b_log.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val message: String,
    val data: LoginData,
)


data class LoginData(

    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("refresh_token")
    val refreshToken: String
)