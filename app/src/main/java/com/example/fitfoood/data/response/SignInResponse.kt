package com.example.fitfoood.data.response

import com.google.gson.annotations.SerializedName

/**
 * Sign in response
 *
 * This data class is used to define sign in response.
 *
 * @property message Response message
 * @property data Tokens
 * @constructor Create empty Sign in response
 */
data class SignInResponse(
	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("data")
	val data: Tokens? = null,
)

/**
 * Tokens
 *
 * This data class is used to define tokens.
 *
 * @property accessToken Access token
 * @property refreshToken Refresh token
 * @constructor Create empty Tokens
 */
data class Tokens(

	@field:SerializedName("accessToken")
	val accessToken: String? = null,

	@field:SerializedName("refreshToken")
	val refreshToken: String? = null,
)
