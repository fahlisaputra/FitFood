package com.example.fitfoood.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fitfoood.data.ApiResponse
import com.example.fitfoood.data.api.enqueueLiveData
import com.example.fitfoood.data.response.ChatHistoryResponse
import com.example.fitfoood.data.response.SendChatResponse
import com.example.fitfoood.source.ApiService

/**
 * Chat Repository
 *
 * @property apiService API Service
 * @constructor Create Chat Repository instance
 */
class ChatRepository(
    private val apiService: ApiService,
) {

    /**
     * Send message
     *
     * @param message Message to send
     * @return Chat Response
     */
    fun sendMessage(message: String): LiveData<ApiResponse<SendChatResponse>> {
        val result = MutableLiveData<ApiResponse<SendChatResponse>>()
        val client = apiService.sendChatMessage(message)
        client.enqueueLiveData(result)
        return result
    }

    /**
     * Get messages
     *
     * @return Chat History Response
     */
    fun getMessages(): LiveData<ApiResponse<ChatHistoryResponse>> {
        val result = MutableLiveData<ApiResponse<ChatHistoryResponse>>()
        val client = apiService.getChatMessages()
        client.enqueueLiveData(result)
        return result
    }

}
