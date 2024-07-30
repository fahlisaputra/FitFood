package com.example.fitfoood.data.source

import com.example.fitfoood.data.response.SignInResponse
import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiServiceToken {
    @Headers("Cache-control: no-cache")
    @POST("v2/auth/token")
    fun refreshToken(): Call<SignInResponse>
}