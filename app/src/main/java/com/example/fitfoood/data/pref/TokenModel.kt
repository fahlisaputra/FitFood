package com.example.fitfoood.data.pref

/**
 * Token model
 *
 * This data class is used to define token model.
 *
 * @property accessToken Access token
 * @property refreshToken Refresh token
 * @constructor Create empty Token model
 */
data class TokenModel(
    val accessToken: String?,
    val refreshToken: String?
)