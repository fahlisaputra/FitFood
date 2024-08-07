package com.example.fitfoood.view.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitfoood.adapter.ChatAdapter
import com.example.fitfoood.data.ApiResponse
import com.example.fitfoood.data.models.ChatModel
import com.example.fitfoood.databinding.ActivityChatBinding
import com.example.fitfoood.utils.DateTime
import com.example.fitfoood.view.ViewModelFactory

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var viewModel: ChatViewModel
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelFactory.getInstance(this).create(ChatViewModel::class.java)

        chatAdapter = ChatAdapter()

        binding.btnSend.setOnClickListener {
            val message = binding.inputMessageEditText.text.toString()
            if (message.isNotEmpty()) {
                sendMessage(message)
                binding.inputMessageEditText.text.clear()
            }
        }


        setupRecyclerView()
        retrieveOldMessages()
    }

    private fun setupRecyclerView() {
        with(binding.rvChatHistory) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            this.adapter = chatAdapter
        }
    }

    private fun retrieveOldMessages() {
        viewModel.getMessages().observe(this) { result ->
            when(result) {
                is ApiResponse.Loading -> {}
                is ApiResponse.Error -> {}
                is ApiResponse.Success -> {
                    val messages = result.data?.data?.messages
                    messages?.forEach {
                        var type = "outgoing"
                        if (it.userId == "BOT") {
                            type = "incoming"
                        }
                        val time = DateTime.fromUTC(it.createdAt ?: "").toString()
                        val chatModel = ChatModel(type, it.text ?: "", time)
                        chatAdapter.addChat(chatModel)
                    }
                }
            }
        }
    }
    private fun sendMessage(message: String) {
        val chatModel = ChatModel("outgoing", message, DateTime.now().toString())
        chatAdapter.addChat(chatModel)
        binding.rvChatHistory.scrollToPosition(chatAdapter.itemCount - 1)

        viewModel.sendMessage(message).observe(this) {result ->
            when(result) {
                is ApiResponse.Loading -> {}
                is ApiResponse.Error -> {}
                is ApiResponse.Success -> {
                    val responseChat = ChatModel("incoming", result.data?.data?.botResponse?.text ?: "", DateTime.now().toString())
                    chatAdapter.addChat(responseChat)
                    binding.rvChatHistory.scrollToPosition(chatAdapter.itemCount - 1)
                }
            }
        }
    }
}