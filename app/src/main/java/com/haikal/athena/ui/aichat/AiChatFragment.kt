package com.haikal.athena.ui.aichat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.haikal.athena.databinding.FragmentAichatBinding

class AiChatFragment : Fragment() {

    private var _binding: FragmentAichatBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val aiChatViewModel =
            ViewModelProvider(this).get(AiChatViewModel::class.java)

        _binding = FragmentAichatBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textAiChat
        aiChatViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}