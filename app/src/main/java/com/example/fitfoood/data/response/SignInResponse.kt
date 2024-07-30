package com.example.fitfoood.data.response

import com.google.gson.annotations.SerializedName

data class SignInResponse(
	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("data")
	val data: Tokens? = null,
)

data class Tokens(

	@field:SerializedName("accessToken")
	val accessToken: String? = null,

	@field:SerializedName("refreshToken")
	val refreshToken: String? = null,
)
