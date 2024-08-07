package com.example.fitfoood.view.chat

import androidx.lifecycle.ViewModel
import com.example.fitfoood.data.repository.ChatRepository
import com.example.fitfoood.data.repository.UserRepository

class ChatViewModel(
    private val userRepository: UserRepository,
    private val chatRepository: ChatRepository) : ViewModel() {

    fun sendMessage(message: String) = chatRepository.sendMessage(message)

    fun getMessages() = chatRepository.getMessages()
}