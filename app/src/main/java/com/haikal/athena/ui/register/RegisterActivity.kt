package com.haikal.athena.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.haikal.athena.databinding.ActivityRegisterBinding
import com.haikal.athena.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.tvRegisterToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            Toast.makeText(this, "Back to Login", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}