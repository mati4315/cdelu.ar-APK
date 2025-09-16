package com.example.cdeluapk.di

import com.example.cdeluapk.data.api.ApiService
import com.example.cdeluapk.data.preferences.AuthPreferences
// import dagger.Module
// import dagger.Provides
// import dagger.hilt.InstallIn
// import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// import javax.inject.Singleton

// @Module
// @InstallIn(SingletonComponent::class)
object NetworkModule {

    // @Provides
    // @Singleton
    fun provideOkHttpClient(authPreferences: AuthPreferences): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                
                // Agregar token de autorización si existe
                val token = authPreferences.getToken()
                if (!token.isNullOrEmpty()) {
                    requestBuilder.addHeader("Authorization", "Bearer $token")
                }
                
                chain.proceed(requestBuilder.build())
            }
            .build()
    }


    // @Provides
    // @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://localhost:3001/api/v1/") // Cambiar por tu URL de producción
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // @Provides
    // @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    // El token se obtiene directamente desde AuthPreferences en el interceptor
}
