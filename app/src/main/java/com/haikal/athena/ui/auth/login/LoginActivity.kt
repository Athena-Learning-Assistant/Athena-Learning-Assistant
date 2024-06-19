package com.haikal.athena.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.haikal.athena.MainActivity
import com.haikal.athena.databinding.ActivityLoginBinding
import com.haikal.athena.ui.forgotPassword.ForgotPasswordActivity
import com.haikal.athena.ui.auth.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
        }

        binding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
            Toast.makeText(this, "Reset Password", Toast.LENGTH_SHORT).show()
        }

        binding.tvLoginToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            Toast.makeText(this, "Register an Account", Toast.LENGTH_SHORT).show()
        }
    }
}