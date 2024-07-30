package com.example.fitfoood.source

import com.example.fitfoood.data.api.AuthInterceptor
import com.example.fitfoood.data.pref.UserPreference
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    /**
     * Base URL for API
     */
    private const val BASE_URL = "http://2.2.2.15:3000/api/"

    /**
     * Provide OkHttp builder with logging interceptor
     *
     * @return OkHttpClient.Builder
     */
    private fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
    }

    /**
     * Provide Retrofit instance
     *
     * @param preferences User preference instance
     * @param useCredential If true, add AuthInterceptor to OkHttpClient
     * @return Retrofit instance
     */
    private fun provideRetrofit(
        preferences: UserPreference,
        useCredential: Boolean = true,
    ): Retrofit {

        val builder = provideOkHttpClientBuilder()
        if (useCredential) {
            builder.addInterceptor(AuthInterceptor(preferences))
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provide Api Service
     *
     * @param preferences User preference instance
     * @return ApiService instance
     */
    fun provideApiService(preferences: UserPreference): ApiService {
        return provideRetrofit(preferences)
            .create(ApiService::class.java)
    }

}