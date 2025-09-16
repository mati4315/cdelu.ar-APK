package com.example.cdeluapk.data.model

import com.google.gson.annotations.SerializedName

data class Comment(
    val id: Int,
    val message: String,
    @SerializedName("user_id")
    val userId: Int,
    val user: User? = null,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String? = null
)