package com.haikal.athena.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.haikal.athena.OCRActivity
import com.haikal.athena.QAActivity
import com.haikal.athena.TGActivity
import com.haikal.athena.TSActivity
import com.haikal.athena.adapter.ChatHistoryAdapter
import com.haikal.athena.adapter.ChatHistoryItem
import com.haikal.athena.databinding.ActivityMainBinding
import com.haikal.athena.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val chatHistoryAdapter: ChatHistoryAdapter by lazy {
        ChatHistoryAdapter(generateDummyChatHistory())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        with(binding.chatHistoryRecyclerView) {
            adapter = chatHistoryAdapter
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
        }

        binding.ocrButton.setOnClickListener {
            startActivity(Intent(requireContext(), OCRActivity::class.java))
        }

        binding.qaButton.setOnClickListener {
            startActivity(Intent(requireContext(), QAActivity::class.java))
        }

        binding.tsButton.setOnClickListener {
            startActivity(Intent(requireContext(), TSActivity::class.java))
        }

        binding.tgButton.setOnClickListener {
            startActivity(Intent(requireContext(), TGActivity::class.java))
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun generateDummyChatHistory(): List<ChatHistoryItem> {
        return List(5) {
            ChatHistoryItem("Make me a question from the document\n\nQuestion:\nGive two examples of abstract nouns.\n\nAnswer:\nLove, happiness.")
        }
    }
}