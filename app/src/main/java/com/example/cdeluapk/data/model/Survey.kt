package com.example.cdeluapk.data.model

import com.google.gson.annotations.SerializedName

data class Survey(
    val id: Int,
    val question: String,
    val options: List<SurveyOption> = emptyList(),
    @SerializedName("total_votes")
    val totalVotes: Int = 0,
    @SerializedName("user_vote")
    val userVote: Int? = null,
    @SerializedName("is_active")
    val isActive: Boolean = true,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class SurveyOption(
    val id: Int,
    @SerializedName("option_text")
    val optionText: String,
    @SerializedName("votes_count")
    val votesCount: Int = 0,
    @SerializedName("survey_id")
    val surveyId: Int
)

data class SurveyVote(
    @SerializedName("option_id")
    val optionId: Int
)
