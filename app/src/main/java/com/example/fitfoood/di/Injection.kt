package com.example.fitfoood.di

import android.content.Context
import com.example.fitfoood.data.pref.BMIPreference
import com.example.fitfoood.data.repository.UserRepository
import com.example.fitfoood.data.pref.UserPreference
import com.example.fitfoood.data.pref.dataStore
import com.example.fitfoood.data.pref.dataStore2
import com.example.fitfoood.data.repository.ArticleRepository
import com.example.fitfoood.data.repository.AuthRepository
import com.example.fitfoood.data.repository.BMIRepository
import com.example.fitfoood.data.repository.InternetRepository
import com.example.fitfoood.data.source.ApiConfigUser
import com.example.fitfoood.source.ApiConfig

object Injection {

    private fun getApiService(pref: UserPreference) = ApiConfig.provideApiService(pref)

    fun provideArtikelRepository(context: Context): ArticleRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = getApiService(pref)
        return ArticleRepository(apiService)
    }
    fun provideUserRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = getApiService(pref)
        return UserRepository.getInstance(pref, apiService)
    }
    fun provideAuthRepository(context: Context): AuthRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = getApiService(pref)
        return AuthRepository(apiService)
    }
    fun provideBMIRRepository(context: Context): BMIRepository {
        val pref = BMIPreference.getInstance(context.dataStore2)
        return BMIRepository.getInstance(pref)
    }

    fun provideInternetRepository(context: Context): InternetRepository {
        return InternetRepository(context)
    }
}