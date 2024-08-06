package com.example.fitfoood.data.response

import com.google.gson.annotations.SerializedName

/**
 * Sign up response
 *
 * This data class is used to define sign up response.
 *
 * @property message Response message
 * @property data User data
 * @constructor Create empty Sign up response
 */
data class SignUpResponse(
	@field:SerializedName("message")
	val message : String,

	@field:SerializedName("data")
	val data: SignUpResponseData
)

/**
 * User data
 *
 * This data class is used to define user data.
 *
 * @property id User id
 * @property name User name
 * @property email User email
 * @constructor Create empty User data
 */
data class SignUpResponseData(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("email")
	val email: String,
)
