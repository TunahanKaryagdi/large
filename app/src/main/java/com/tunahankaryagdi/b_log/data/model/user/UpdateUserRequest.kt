package com.tunahankaryagdi.b_log.data.model.user

import com.google.gson.annotations.SerializedName

data class UpdateUserRequest(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val email: String,
)