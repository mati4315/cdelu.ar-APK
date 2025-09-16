package com.example.cdeluapk.data.model

import com.google.gson.annotations.SerializedName

data class Lottery(
    val id: Int,
    val title: String,
    val description: String? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    @SerializedName("is_free")
    val isFree: Boolean = true,
    @SerializedName("ticket_price")
    val ticketPrice: Double = 0.0,
    @SerializedName("max_tickets")
    val maxTickets: Int,
    @SerializedName("tickets_sold")
    val ticketsSold: Int = 0,
    @SerializedName("num_winners")
    val numWinners: Int = 1,
    @SerializedName("prize_description")
    val prizeDescription: String? = null,
    val status: String = "active", // active, completed, cancelled
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class LotteryParticipation(
    @SerializedName("lottery_id")
    val lotteryId: Int,
    @SerializedName("num_tickets")
    val numTickets: Int = 1
)

data class LotteryWinner(
    val id: Int,
    @SerializedName("lottery_id")
    val lotteryId: Int,
    @SerializedName("user_id")
    val userId: Int,
    val user: User? = null,
    @SerializedName("ticket_number")
    val ticketNumber: String,
    @SerializedName("created_at")
    val createdAt: String
)
