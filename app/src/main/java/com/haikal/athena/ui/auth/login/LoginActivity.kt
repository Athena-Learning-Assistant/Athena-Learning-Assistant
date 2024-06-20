package com.haikal.athena.ui.auth.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.haikal.athena.ui.main.MainActivity
import com.haikal.athena.R
import com.haikal.athena.data.RequestInformation
import com.haikal.athena.data.local.pref.SessionManager
import com.haikal.athena.databinding.ActivityLoginBinding
import com.haikal.athena.ui.ViewModelFactory
import com.haikal.athena.ui.auth.register.RegisterActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel: LoginViewModel by viewModels { ViewModelFactory.getInstance(this) }
    private lateinit var sessionManager: SessionManager
    private val fields: List<View> by lazy {
        listOf(
            binding.ivLogin,
            binding.tvLogin,
            binding.emailEditTextLayout,
            binding.passwordEditTextLayout,
            binding.btnLogin,
            binding.tvLoginToRegisterHint,
            binding.tvLoginToRegister
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        sessionManager = SessionManager(this)
        checkLoggedIn()

        binding.btnLogin.setOnClickListener(this)
        binding.tvLoginToRegister.setOnClickListener(this)
        configureObserver()

        enableFields(true)
        playAnimation(true)
    }

    private fun checkLoggedIn() {
        lifecycleScope.launch {
            sessionManager.authToken.collect { authToken ->
                if (!authToken.isNullOrEmpty()) {
                    // Jika token tersedia, lanjutkan ke MainActivity
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
            }
        }
    }

    private fun login(email: String, password: String) {
        viewModel.login(email, password)
    }

    override fun onClick(view: View) {
        val binding = binding
        val email = binding.edLoginEmail.text.toString().trim()
        val password = binding.edLoginPassword.text.toString().trim()

        when (view.id) {
            binding.btnLogin.id -> {
                if (!viewModel.loginIsValid(binding)) {
                    Toast.makeText(
                        this@LoginActivity,
                        getString(R.string.login_invalid_input_toast),
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }
                login(email, password)
            }

            binding.tvLoginToRegister.id -> {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                startActivity(intent)
            }
        }
    }

    private fun playAnimation(visible: Boolean, duration: Long = 500) {
        val targetValue = if (visible) 1f else 0f
        val animations = fields.map {
            ObjectAnimator.ofFloat(it, View.ALPHA, targetValue).setDuration(duration)
        }

        var it = 0
        val logoAnim = animations[it++]
        val titleAnim = animations[it++]
        val emailAnim = animations[it++]
        val passwdAnim = animations[it++]
        val btnLoginAnim = animations[it++]
        val btnRegisterHintAnim = animations[it++]
        val btnRegisterAnim = animations[it]

        AnimatorSet().apply {
            playSequentially(
                logoAnim,
                titleAnim,
                emailAnim,
                passwdAnim,
                btnLoginAnim,
                btnRegisterHintAnim,
                btnRegisterAnim
            )
        }.start()
    }

    override fun onResume() {
        super.onResume()
        enableFields(true)
        playAnimation(true, 200)
    }

    override fun onPause() {
        super.onPause()
        enableFields(false)
        playAnimation(false, 0)
    }

    private fun configureObserver() = viewModel.loginStatus.observe(this) { result ->
        when (result) {
            is RequestInformation.Loading -> {
                playAnimation(false, 200)
                enableFields(false)
                showLoading(true)
            }

            is RequestInformation.Error -> {
                Toast.makeText(
                    this@LoginActivity,
                    getString(R.string.login_failed_toast, result.error),
                    Toast.LENGTH_SHORT
                ).show()
                enableFields(true)
                playAnimation(true, 220)
                showLoading(false)
            }

            is RequestInformation.Success -> {
                Toast.makeText(
                    this,
                    getString(R.string.login_greetings_toast, result.data.name),
                    Toast.LENGTH_SHORT
                ).show()
                playAnimation(true, 200)

                val checkTokenThread = Thread {
                    Thread.sleep(1000)

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
                checkTokenThread.start()
            }
        }
    }

    private fun showLoading(visible: Boolean) {
        binding.loading.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun enableFields(enable: Boolean) {
        fields.forEach {
            it.isEnabled = enable
        }
    }
}