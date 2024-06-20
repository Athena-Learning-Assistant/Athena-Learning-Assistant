package com.haikal.athena.ui.main.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.haikal.athena.R
import com.haikal.athena.adapter.ChatHistoryAdapter
import com.haikal.athena.databinding.ActivityHistoriQaBinding
import com.haikal.athena.databinding.FragmentHomeBinding

class HistoriQaActivity : AppCompatActivity() {
    private var _binding: ActivityHistoriQaBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHistoriQaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        enableEdgeToEdge()

        val adapter = ChatHistoryAdapter(DummyData.data)
        with(binding.chatHistoryRecyclerView) {
            layoutManager = LinearLayoutManager(this@HistoriQaActivity)
            this.adapter = adapter
        }

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, HomeFragment::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}