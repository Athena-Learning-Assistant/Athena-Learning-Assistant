package com.haikal.athena.ui.auth.forgotPassword

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.haikal.athena.databinding.ActivityForgotPasswordBinding
import com.haikal.athena.ui.auth.login.LoginActivity

class ForgotPasswordActivity : AppCompatActivity() {
    private val binding by lazy { ActivityForgotPasswordBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnResetPassword.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            Toast.makeText(this, "Password has Been Changed", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}