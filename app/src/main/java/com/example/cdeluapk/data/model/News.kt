package com.example.cdeluapk.data.model

import com.google.gson.annotations.SerializedName

data class News(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    @SerializedName("video_url")
    val videoUrl: String? = null,
    @SerializedName("image_urls")
    val imageUrls: List<String> = emptyList(),
    @SerializedName("user_id")
    val userId: Int,
    val user: User? = null,
    @SerializedName("likes_count")
    val likesCount: Int = 0,
    @SerializedName("comments_count")
    val commentsCount: Int = 0,
    @SerializedName("is_liked")
    val isLiked: Boolean = false,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class NewsWithPagination(
    val data: List<News>,
    val current_page: Int,
    val last_page: Int,
    val per_page: Int,
    val total: Int
)
