package com.haikal.athena.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.haikal.athena.ui.features.ocr.OCRActivity
import com.haikal.athena.ui.features.qa.QAActivity
import com.haikal.athena.adapter.ChatHistoryAdapter
import com.haikal.athena.adapter.ChatHistoryItem
import com.haikal.athena.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        val adapter = ChatHistoryAdapter(DummyData.data.take(3))
        with(binding.chatHistoryRecyclerView) {
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
            this.adapter = adapter
        }

        binding.viewAllButton.setOnClickListener {
            startActivity(Intent(requireContext(), HistoriQaActivity::class.java))
        }

        binding.ocrButton.setOnClickListener {
            startActivity(Intent(requireContext(), OCRActivity::class.java))
        }

        binding.qaButton.setOnClickListener {
            startActivity(Intent(requireContext(), QAActivity::class.java))
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}