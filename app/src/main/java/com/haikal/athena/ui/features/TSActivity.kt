package com.haikal.athena.ui.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.haikal.athena.databinding.ActivityTsBinding

class TSActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}