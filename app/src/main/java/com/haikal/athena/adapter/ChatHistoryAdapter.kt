package com.haikal.athena.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haikal.athena.databinding.ChatHistoryItemBinding

class ChatHistoryAdapter(private val items: List<ChatHistoryItem>) :
    RecyclerView.Adapter<ChatHistoryAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ChatHistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatHistoryItem) {
            binding.chatHistoryQuestionText.text = item.question
            binding.chatHistoryAnswerText.text = item.answer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ChatHistoryItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}