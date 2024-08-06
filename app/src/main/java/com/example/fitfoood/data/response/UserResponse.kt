package com.example.fitfoood.data.response

import com.google.gson.annotations.SerializedName

/**
 * User response
 *
 * This data class is used to define user response.
 *
 * @property data User data
 * @constructor Create empty User response
 */
data class UserResponse(

	@field:SerializedName("data")
	val data: UserDataResponse? = null
)

/**
 * User data response
 *
 * This data class is used to define user data response.
 *
 * @property user User model
 * @constructor Create empty User data response
 */
data class UserDataResponse(

	@field:SerializedName("user")
	val user: UserModel? = null

)

/**
 * User model
 *
 * This data class is used to define user model.
 *
 * @property id User id
 * @property email User email
 * @property name User name
 * @constructor Create empty User model
 */
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


