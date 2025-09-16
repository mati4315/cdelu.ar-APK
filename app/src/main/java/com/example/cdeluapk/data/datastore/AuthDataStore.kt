package com.example.cdeluapk.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
// import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
// import javax.inject.Inject
// import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

private object PreferencesKeys {
    val TOKEN = stringPreferencesKey("token")
}

// @Singleton
class AuthDataStore /* @Inject constructor(
    @ApplicationContext private val context: Context
) */ {
    
    // Constructor temporal sin DI
    private lateinit var context: Context
    
    constructor(context: Context) {
        this.context = context
    }
    
    val token: Flow<String?> by lazy {
        context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.TOKEN]
        }
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.TOKEN] = token
        }
    }

    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(PreferencesKeys.TOKEN)
        }
    }
}