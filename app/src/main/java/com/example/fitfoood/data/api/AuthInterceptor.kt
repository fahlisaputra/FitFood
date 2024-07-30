package com.example.fitfoood.data.api

import com.example.fitfoood.data.pref.UserPreference
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

/**
 * AuthInterceptor
 *
 * This class is used to intercept the request and add authorization header
 *
 * @property preferences User Preference
 * @constructor Create Auth Interceptor instance
 */
class AuthInterceptor(private val preferences: UserPreference) : Interceptor {

    /**
     * List of urls that doesn't require authorization
     */
    private val excludeUrls = listOf(
        "auth/sign-in",
        "auth/sign-up"
    )

    /**
     * List of urls that use refresh token
     */
    private val urlUseRefreshToken = listOf(
        "auth/token"
    )

    /**
     * Authorization header name
     */
    private val headerName = "Authorization"

    /**
     * Intercept the request and add authorization header
     *
     * @param chain Interceptor.Chain
     * @return Response
     */
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