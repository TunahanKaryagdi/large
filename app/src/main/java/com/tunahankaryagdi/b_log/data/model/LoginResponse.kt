package com.tunahankaryagdi.b_log.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val message: String,
    val data: Data,
)


data class Data(

    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("refresh_token")
    val refreshToken: String
)