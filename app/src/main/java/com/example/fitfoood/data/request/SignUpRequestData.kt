package com.example.fitfoood.data.request

import com.google.gson.annotations.SerializedName

/**
 * Data class that captures user information for signup request
 *
 * @property name user's name
 * @property email user's email
 * @property password user's password
 * @property profile user's profile
 * @see SignUpProfileData for more information
 */
data class SignUpRequestData(
    val name: String,
    val email: String,
    val password: String,

    @field:SerializedName("profile")
    val profile: SignUpProfileData
)

/**
 * Data class that captures user's profile information for signup request
 *
 * @property birthDate user's birth date
 * @property gender user's gender
 * @property weight user's weight
 * @property height user's height
 */
data class SignUpProfileData(
    @field:SerializedName("birth_date")
    val birthDate: String,
    val gender: String,
    val weight: Int,
    val height: Int,
)