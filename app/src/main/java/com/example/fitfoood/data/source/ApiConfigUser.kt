package com.example.fitfoood.data.source

import com.example.fitfood.data.source.ApiServiceUser
import com.example.fitfoood.data.api.AuthInterceptor
import com.example.fitfoood.data.pref.UserPreference
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfigUser {

//    /**
//     * Base URL for API
//     */
//    private const val BASE_URL = "http://2.2.2.15:3000/api/"
//
//    /**
//     * Provide OkHttp builder with logging interceptor
//     *
//     * @return OkHttpClient.Builder
//     */
//    private fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
//        return OkHttpClient.Builder()
//            .addInterceptor(HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.BODY
//            })
//    }
//
//    /**
//     * Provide Retrofit instance
//     *
//     * @param preferences User preference instance
//     * @param useCredential If true, add AuthInterceptor to OkHttpClient
//     * @param useRefreshToken Use refresh token instead of access token
//     * @return Retrofit instance
//     */
//    private fun provideRetrofit(
//        preferences: UserPreference,
//        useCredential: Boolean = true,
//        useRefreshToken: Boolean = false
//    ): Retrofit {
//
//        val builder = provideOkHttpClientBuilder()
//        if (useCredential) {
//            builder.addInterceptor(AuthInterceptor(preferences, useRefreshToken))
//        }
//
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(builder.build())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    /**
//     * Provide ApiServiceToken
//     *
//     * @param preferences User preference instance
//     * @return ApiServiceUser instance
//     */
//    fun provideApiServiceToken(preferences: UserPreference): ApiServiceToken {
//        return provideRetrofit(preferences, useRefreshToken = true)
//            .create(ApiServiceToken::class.java)
//    }
//
//    /**
//     * Provide ApiServiceUser
//     *
//     * @param preferences User preference instance
//     * @return ApiServiceUser instance
//     */
//    fun provideApiServiceUser(preferences: UserPreference): ApiServiceUser {
//        return provideRetrofit(preferences, true)
//            .create(ApiServiceUser::class.java)
//    }
}