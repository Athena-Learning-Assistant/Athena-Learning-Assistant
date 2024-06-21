package com.haikal.athena.ui.main.aichat

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.haikal.athena.adapter.Message
import com.haikal.athena.adapter.MessageAdapter
import com.haikal.athena.adapter.MessageType
import com.haikal.athena.databinding.FragmentAichatBinding
import com.haikal.athena.ui.features.cam.CamActivity

class AiChatFragment : Fragment() {

    private var _binding: FragmentAichatBinding? = null
    private val binding get() = _binding!!
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var aiChatViewModel: AiChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAichatBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize ViewModel
        aiChatViewModel = ViewModelProvider(this).get(AiChatViewModel::class.java)

        setupRecyclerView()
        setupSendButton()

        // Add welcome message
        aiChatViewModel.addMessage(Message("Hai, I am Athena. Please provide questions for me to answer.", MessageType.RECEIVED))

        binding.clearButton.setOnClickListener {
            aiChatViewModel.clearMessages()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupRecyclerView() {
        messageAdapter = MessageAdapter(aiChatViewModel.messages.value ?: mutableListOf())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = messageAdapter
        }

        // Observe changes in messages list
        aiChatViewModel.messages.observe(viewLifecycleOwner) { messages ->
            messageAdapter.messages = messages
            messageAdapter.notifyDataSetChanged()
            binding.recyclerView.scrollToPosition(messages.size - 1)
        }
    }

    private fun setupSendButton() {
        binding.sendButton.setOnClickListener {
            val messageText = binding.messageInput.text.toString().trim()
            if (!TextUtils.isEmpty(messageText)) {
                val message = Message(messageText, MessageType.SENT)
                aiChatViewModel.addMessage(message)
                val response = aiChatViewModel.generateResponse(messageText)
                aiChatViewModel.addMessage(Message(response, MessageType.RECEIVED))
                binding.messageInput.text?.clear()
            }
        }
    }
}