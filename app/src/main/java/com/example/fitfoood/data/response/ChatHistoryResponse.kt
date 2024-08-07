package com.example.fitfoood.data.response

import com.google.gson.annotations.SerializedName

data class ChatHistoryResponse(
    @field:SerializedName("data")
    val data: ChatHistoryDataResponse? = null
)

data class ChatHistoryDataResponse(
    @field:SerializedName("messages")
    val messages: List<ChatHistoryMessageResponse>? = null
)

data class ChatHistoryMessageResponse(

    @field:SerializedName("user_id")
    val userId: String? = null,

    val text: String? = null,

    val createdAt: String? = null
)