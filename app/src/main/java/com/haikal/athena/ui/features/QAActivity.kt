package com.haikal.athena.ui.features

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.haikal.athena.adapter.Message
import com.haikal.athena.adapter.MessageAdapter
import com.haikal.athena.adapter.MessageType
import com.haikal.athena.databinding.ActivityQaBinding

class QAActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQaBinding
    private val messages = mutableListOf<Message>()
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupSendButton()
    }

    private fun setupRecyclerView() {
        messageAdapter = MessageAdapter(messages)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@QAActivity)
            adapter = messageAdapter
        }
    }

    private fun setupSendButton() {
        binding.sendButton.setOnClickListener {
            val messageText = binding.messageInput.text.toString().trim()
            if (!TextUtils.isEmpty(messageText)) {
                addMessage(Message(messageText, MessageType.SENT))
                // Here you can add logic to get the response from the bot
                addMessage(Message("This is a response from the bot", MessageType.RECEIVED))
                binding.messageInput.text.clear()
            }
        }
    }

    private fun addMessage(message: Message) {
        messages.add(message)
        messageAdapter.notifyItemInserted(messages.size - 1)
        binding.recyclerView.scrollToPosition(messages.size - 1)
    }
}

