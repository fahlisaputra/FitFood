package com.example.fitfoood.data.response

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
	@field:SerializedName("message")
	val message : String,

	@field:SerializedName("data")
	val data: SignUpResponseData
)

data class SignUpResponseData(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("email")
	val email: String,
)
