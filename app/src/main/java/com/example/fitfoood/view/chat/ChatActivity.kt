package com.example.fitfoood.view.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitfoood.adapter.ArticleCardAdapter
import com.example.fitfoood.adapter.ChatAdapter
import com.example.fitfoood.data.models.ChatModel
import com.example.fitfoood.databinding.ActivityChatBinding
import com.example.fitfoood.view.ViewModelFactory
import com.example.fitfoood.view.main.HomeViewModel

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var viewModel: ChatViewModel
    private lateinit var chatAdapter: ChatAdapter
    private val chatList = mutableListOf<ChatModel>()

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
    }

    private fun setupRecyclerView() {
        with(binding.rvChatHistory) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            this.adapter = chatAdapter
        }
    }

    private fun sendMessage(message: String) {
        val chatModel = ChatModel("outgoing", message, "12:00")
        chatList.add(chatModel)
        chatAdapter.addChat(chatModel)
    }
}