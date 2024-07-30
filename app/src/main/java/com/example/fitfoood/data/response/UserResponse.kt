package com.example.fitfoood.data.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("data")
	val data: UserDataResponse? = null
)

data class UserDataResponse(

	@field:SerializedName("user")
	val user: UserModel? = null

)

data class UserModel(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("name")
	val name: String? = null,
)

data class DateOfBirth(

	@field:SerializedName("_nanoseconds")
	val nanoseconds: Int? = null,

	@field:SerializedName("_seconds")
	val seconds: Int? = null
)


