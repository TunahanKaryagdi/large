package com.tunahankaryagdi.b_log.data.model.user

import com.google.gson.annotations.SerializedName

data class SignupRequest(
    @SerializedName("first_name")
    val first_name: String,
    @SerializedName("last_name")
    val last_name: String,
    val email: String,
    val password: String
)