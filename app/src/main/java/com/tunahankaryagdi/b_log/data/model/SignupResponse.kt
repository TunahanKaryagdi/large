package com.tunahankaryagdi.b_log.data.model

import com.google.gson.annotations.SerializedName

data class SignupResponse(
    val data: SignupDto? =null,
    val message: String
)


data class SignupDto(
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    val id: String,
    @SerializedName("last_name")
    val lastName: String,
    val password: String,
    val role: String
)