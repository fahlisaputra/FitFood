package com.example.fitfoood.data.pref

import com.example.fitfoood.data.response.DateOfBirth

data class UserModel(
    val name: String?,
    val email: String?,
    val userId: String?,
    val isLogin: Boolean = false
)