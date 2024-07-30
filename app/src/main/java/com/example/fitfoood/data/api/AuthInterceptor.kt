package com.example.fitfoood.data.api

import com.example.fitfoood.data.pref.UserPreference
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val preferences: UserPreference) : Interceptor {

    private val excludeUrls = listOf(
        "auth/sign-in",
        "auth/sign-up"
    )

    private val urlUseRefreshToken = listOf(
        "auth/token"
    )

    private val headerName = "Authorization"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        return runBlocking {
            val token = preferences.getToken().first()

            // check if url contains sign in or sign up
            val url = chain.request().url.toString()
            if (!excludeUrls.any { url.contains(it) }) {
                if (urlUseRefreshToken.any { url.contains(it) }) {
                    request.addHeader(headerName, "Bearer ${token.refreshToken}")
                } else {
                    request.addHeader(headerName, "Bearer ${token.accessToken}")
                }
            }

            chain.proceed(request.build())
        }
    }

}