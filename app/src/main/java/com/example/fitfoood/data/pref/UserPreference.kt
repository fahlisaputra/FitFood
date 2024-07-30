package com.example.fitfoood.data.pref

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_data")

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    fun getUser(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[NAME] ?: "",
                preferences[EMAIL] ?: "",
                preferences[USER_ID] ?: "",
            )
        }
    }

    fun getToken(): Flow<TokenModel> {
        return dataStore.data.map { preferences ->
            TokenModel(
                preferences[ACCESS_TOKEN] ?: "",
                preferences[REFRESH_TOKEN] ?: ""
            )
        }
    }

    suspend fun saveToken(token: TokenModel) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = token.accessToken ?: ""
            preferences[REFRESH_TOKEN] = token.refreshToken ?: ""
        }
    }

    suspend fun saveSession(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[NAME] = user.name ?: ""
            preferences[EMAIL] = user.email ?: ""
            preferences[USER_ID] = user.userId ?: ""
            preferences[IS_LOGIN] = true
        }
    }

    fun getSession(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[NAME] ?: "",
                preferences[EMAIL] ?: "",
                preferences[USER_ID] ?: "",
                preferences[IS_LOGIN] ?: false
            )
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            val email = preferences[EMAIL] ?: ""
            val userId = preferences[USER_ID] ?: ""
            val name = preferences[NAME] ?: ""
            val accessToken = preferences[ACCESS_TOKEN] ?: ""
            val refreshToken = preferences[REFRESH_TOKEN] ?: ""
            preferences.clear()
            preferences[EMAIL] = email
            preferences[USER_ID] = userId
            preferences[NAME] = name
            preferences[ACCESS_TOKEN] = accessToken
            preferences[REFRESH_TOKEN] = refreshToken
        }
    }




    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val NAME = stringPreferencesKey("name")
        private val EMAIL = stringPreferencesKey("email")
        private val USER_ID = stringPreferencesKey("userId")
        private val IS_LOGIN = booleanPreferencesKey("isLogin")
        private val ACCESS_TOKEN = stringPreferencesKey("accessToken")
        private val REFRESH_TOKEN = stringPreferencesKey("refreshToken")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}