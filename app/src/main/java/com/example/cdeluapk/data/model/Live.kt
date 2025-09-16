package com.example.cdeluapk.data.model

import com.google.gson.annotations.SerializedName

data class LiveStatus(
    @SerializedName("is_live")
    val isLive: Boolean = false,
    val title: String? = null,
    @SerializedName("embed_url")
    val embedUrl: String? = null,
    @SerializedName("hls_url")
    val hlsUrl: String? = null,
    @SerializedName("permalink")
    val permalink: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null
)
