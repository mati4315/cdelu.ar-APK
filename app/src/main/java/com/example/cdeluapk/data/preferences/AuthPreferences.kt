package com.example.cdeluapk.data.preferences

import android.content.Context
import android.content.SharedPreferences
// import dagger.hilt.android.qualifiers.ApplicationContext
// import javax.inject.Inject
// import javax.inject.Singleton

// @Singleton
class AuthPreferences /* @Inject constructor(
    @ApplicationContext private val context: Context
) */ {
    
    // Constructor temporal sin DI
    private lateinit var context: Context
    
    constructor(context: Context) {
        this.context = context
    }
    
    private val prefs: SharedPreferences by lazy { 
        context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE) 
    }

    fun getToken(): String? = prefs.getString("token", null)

    fun saveToken(token: String) {
        prefs.edit().putString("token", token).apply()
    }

    fun clearToken() {
        prefs.edit().remove("token").apply()
    }
}