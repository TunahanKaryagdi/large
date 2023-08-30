package com.tunahankaryagdi.b_log.data.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthDataStore(private val context :Context) {


    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user")
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    }

    val getAccessToken: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[ACCESS_TOKEN_KEY] ?: ""
    }

    val getRefreshToken: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[REFRESH_TOKEN_KEY] ?: ""
    }

    suspend fun saveTokens(accessToken: String, refreshToken :String) {
        context.dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = accessToken
            preferences[REFRESH_TOKEN_KEY] = refreshToken
        }
    }

}