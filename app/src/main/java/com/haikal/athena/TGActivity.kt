package com.haikal.athena

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.haikal.athena.databinding.ActivityTgBinding

class TGActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTgBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTgBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}