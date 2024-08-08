package com.example.fitfoood.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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

    fun getProfile(): Flow<ProfileModel> {
        return dataStore.data.map { preferences ->
            ProfileModel(
                preferences[PROFILE_BIRTH_DATE] ?: "",
                preferences[PROFILE_GENDER] ?: "",
                preferences[PROFILE_HEIGHT]?.toInt(),
                preferences[PROFILE_WEIGHT]?.toInt(),
                preferences[PROFILE_BMI_INDEX]?.toFloat(),
                preferences[PROFILE_BMI_LABEL] ?: ""
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

    suspend fun saveProfile(profile: ProfileModel) {
        dataStore.edit { preferences ->
            preferences[PROFILE_BIRTH_DATE] = profile.birthDate ?: ""
            preferences[PROFILE_GENDER] = profile.gender ?: ""
            preferences[PROFILE_HEIGHT] = profile.height?.toString() ?: ""
            preferences[PROFILE_WEIGHT] = profile.weight?.toString() ?: ""
            preferences[PROFILE_BMI_INDEX] = profile.bmiIndex?.toString() ?: ""
            preferences[PROFILE_BMI_LABEL] = profile.bmiLabel ?: ""
        }
    }

    suspend fun saveUser(user: UserModel) {
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

        private val PROFILE_BIRTH_DATE = stringPreferencesKey("profileBirthDate")
        private val PROFILE_GENDER = stringPreferencesKey("profileGender")
        private val PROFILE_HEIGHT = stringPreferencesKey("profileHeight")
        private val PROFILE_WEIGHT = stringPreferencesKey("profileWeight")
        private val PROFILE_BMI_INDEX = stringPreferencesKey("profileBMIIndex")
        private val PROFILE_BMI_LABEL = stringPreferencesKey("profileBMILabel")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}