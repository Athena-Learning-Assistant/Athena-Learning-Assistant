package com.haikal.athena.ui.aichat

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.haikal.athena.adapter.Message
import com.haikal.athena.adapter.MessageAdapter
import com.haikal.athena.adapter.MessageType
import com.haikal.athena.databinding.FragmentAichatBinding

class AiChatFragment : Fragment() {

    private var _binding: FragmentAichatBinding? = null
    private val binding get() = _binding!!
    private val messages = mutableListOf<Message>()
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var aiChatViewModel: AiChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        aiChatViewModel = ViewModelProvider(this).get(AiChatViewModel::class.java)
        _binding = FragmentAichatBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()
        setupSendButton()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        messageAdapter = MessageAdapter(messages)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
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