package com.haikal.athena

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.haikal.athena.databinding.ActivityQaBinding

class QAActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}