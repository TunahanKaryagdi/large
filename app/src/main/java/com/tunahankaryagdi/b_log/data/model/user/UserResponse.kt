package com.tunahankaryagdi.b_log.data.model.user

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val user: UserDto,
    val message: String,
    val status: Int
)


data class UserDto(
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    val id: String,
    @SerializedName("last_name")
    val lastName: String,
    val role: String
)