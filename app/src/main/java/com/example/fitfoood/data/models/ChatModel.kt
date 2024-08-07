package com.example.fitfoood.data.models

data class ChatModel(
    var type: String = "outgoing", // incoming or outgoing
    var message: String = "",
    var time: String = ""
)
