package com.example.fitfoood.data.response

import com.google.gson.annotations.SerializedName

data class SendChatResponse(
    @field:SerializedName("data")
    val data: SendChatDataResponse? = null
)

data class SendChatDataResponse(
    @field:SerializedName("bot_response")
    val botResponse: BotDataResponse? = null
)

data class BotDataResponse(
    val id: String? = null,
    val text: String? = null,
)
