package com.example.fitfoood.data.pref

/**
 * User model
 *
 * This data class is used to define user model.
 *
 * @property name User name
 * @property email User email
 * @property userId User id
 * @property isLogin User login status
 * @constructor Create empty User model
 */
data class UserModel(
    val name: String?,
    val email: String?,
    val userId: String?,
    val isLogin: Boolean = false
)