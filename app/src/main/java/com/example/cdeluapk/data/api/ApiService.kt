package com.example.cdeluapk.data.api

import com.example.cdeluapk.data.model.AuthResponse
import com.example.cdeluapk.data.model.Comment
import com.example.cdeluapk.data.model.LiveStatus
import com.example.cdeluapk.data.model.Lottery
import com.example.cdeluapk.data.model.LotteryParticipation
import com.example.cdeluapk.data.model.News
import com.example.cdeluapk.data.model.NewsWithPagination
import com.example.cdeluapk.data.model.Survey
import com.example.cdeluapk.data.model.SurveyVote
import com.example.cdeluapk.data.model.User
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    
    // Autenticación
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<AuthResponse>
    
    @POST("auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<User>
    
    @POST("auth/logout")
    suspend fun logout(): Response<Unit>
    
    // Noticias
    @GET("news")
    suspend fun getNews(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): Response<NewsWithPagination>
    
    @GET("news/{id}")
    suspend fun getNewsById(@Path("id") id: Int): Response<News>
    
    @POST("news")
    suspend fun createNews(@Body newsRequest: CreateNewsRequest): Response<News>
    
    @PUT("news/{id}")
    suspend fun updateNews(
        @Path("id") id: Int,
        @Body newsRequest: UpdateNewsRequest
    ): Response<News>
    
    @DELETE("news/{id}")
    suspend fun deleteNews(@Path("id") id: Int): Response<Unit>
    
    // Comunicaciones
    @Multipart
    @POST("com")
    suspend fun createCommunication(
        @Part("titulo") titulo: RequestBody,
        @Part("descripcion") descripcion: RequestBody,
        @Part video: MultipartBody.Part? = null,
        @Part images: List<MultipartBody.Part>? = null
    ): Response<News>
    
    // Likes
    @POST("news/{id}/like")
    suspend fun likeNews(@Path("id") id: Int): Response<Unit>
    
    @DELETE("news/{id}/like")
    suspend fun unlikeNews(@Path("id") id: Int): Response<Unit>
    
    // Comentarios
    @GET("news/{id}/comments")
    suspend fun getComments(@Path("id") id: Int): Response<List<Comment>>
    
    @POST("news/{id}/comments")
    suspend fun createComment(
        @Path("id") id: Int,
        @Body commentRequest: CreateCommentRequest
    ): Response<Comment>
    
    // Encuestas
    @GET("surveys/active")
    suspend fun getActiveSurveys(): Response<List<Survey>>
    
    @POST("surveys/{id}/vote")
    suspend fun voteSurvey(
        @Path("id") id: Int,
        @Body voteRequest: SurveyVote
    ): Response<Unit>
    
    // Loterías
    @GET("lotteries")
    suspend fun getLotteries(): Response<List<Lottery>>
    
    @GET("lotteries/{id}")
    suspend fun getLotteryById(@Path("id") id: Int): Response<Lottery>
    
    @POST("lotteries/{id}/participate")
    suspend fun participateLottery(
        @Path("id") id: Int,
        @Body participation: LotteryParticipation
    ): Response<Unit>
    
    // Transmisión en vivo
    @GET("live/status")
    suspend fun getLiveStatus(): Response<LiveStatus>
    
    // Usuario
    @GET("user/profile")
    suspend fun getUserProfile(): Response<User>
    
    @PUT("user/profile")
    suspend fun updateUserProfile(@Body userRequest: UpdateUserRequest): Response<User>
    
    @Multipart
    @POST("user/profile-picture")
    suspend fun uploadProfilePicture(@Part image: MultipartBody.Part): Response<User>
}

// Request models
data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    @SerializedName("password_confirmation")
    val passwordConfirmation: String
)

// AuthResponse movido a data/model/AuthResponse.kt

data class CreateNewsRequest(
    val titulo: String,
    val descripcion: String
)

data class UpdateNewsRequest(
    val titulo: String? = null,
    val descripcion: String? = null
)

data class CreateCommentRequest(
    val message: String
)

data class UpdateUserRequest(
    val name: String? = null,
    val email: String? = null
)

