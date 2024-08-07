package com.example.fitfoood.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fitfoood.data.models.ChatModel
import com.example.fitfoood.databinding.ItemMessageAnotherUserBinding
import com.example.fitfoood.databinding.ItemMessageLocalBinding

class ChatAdapter : RecyclerView.Adapter<ViewHolder>() {

    private val listOfChat = mutableListOf<ChatModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            LEFT_VIEW -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemMessageAnotherUserBinding.inflate(inflater, parent, false)
                LeftViewHolder(binding)
            }
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemMessageLocalBinding.inflate(inflater, parent, false)
                RightViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int = listOfChat.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (listOfChat[position].type == "outgoing")
            (holder as RightViewHolder).bind(listOfChat[position], position)
        else
            (holder as LeftViewHolder).bind(listOfChat[position], position)
    }

    override fun getItemViewType(position: Int): Int {
        return if (listOfChat[position].type == "outgoing") RIGHT_VIEW else LEFT_VIEW
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<ChatModel>) {
        listOfChat.clear()
        listOfChat.addAll(list)
        notifyDataSetChanged()
    }

    fun addChat(chat: ChatModel) {
        listOfChat.add(chat)
        notifyItemInserted(listOfChat.size - 1)
    }

    inner class LeftViewHolder(private val binding: ItemMessageAnotherUserBinding) : ViewHolder(binding.root) {
        fun bind(chat: ChatModel, pos: Int) {
            binding.apply {
                chat.also {
                    tvUserMessageText.text = chat.message
                    timeAI.text = chat.time

                    if (pos == 0) {
                        val param = tvUserMessageText.layoutParams as ViewGroup.MarginLayoutParams
                        param.topMargin = 32
                        tvUserMessageText.layoutParams = param
                    }
                }
            }
        }
    }

    inner class RightViewHolder(private val binding: ItemMessageLocalBinding) : ViewHolder(binding.root) {
        fun bind(chat: ChatModel, pos: Int) {
            binding.apply {
                chat.also {
                    tvUserMessageText.text = chat.message
                    timeUser.text = chat.time

                    if (pos == 0) {
                        val param = tvUserMessageText.layoutParams as ViewGroup.MarginLayoutParams
                        param.topMargin = 32
                        tvUserMessageText.layoutParams = param
                    }
                }
            }
        }
    }

    companion object {
        private const val LEFT_VIEW = 0
        private const val RIGHT_VIEW = 1
    }
}