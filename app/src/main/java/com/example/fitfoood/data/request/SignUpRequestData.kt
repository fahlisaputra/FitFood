package com.example.fitfoood.data.request

import com.google.gson.annotations.SerializedName

data class SignUpRequestData(
    val name: String,
    val email: String,
    val password: String,

    @field:SerializedName("profile")
    val profile: SignUpProfileData
)

data class SignUpProfileData(
    @field:SerializedName("birth_date")
    val birthDate: String,
    val gender: String,
    val weight: Int,
    val height: Int,
)