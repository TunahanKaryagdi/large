package com.tunahankaryagdi.b_log.data.model.user

import com.google.gson.annotations.SerializedName

data class UpdateUserResponse(
    @SerializedName("data")
    val updatedUser: UserDto,
    val message: String
)


data class UpdateUserDto(
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    val id: String,
    @SerializedName("last_name")
    val lastName: String,
    val password: String,
    val role: String
)