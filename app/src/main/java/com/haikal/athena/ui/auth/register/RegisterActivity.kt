package com.haikal.athena.ui.auth.register

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.haikal.athena.R
import com.haikal.athena.data.RequestInformation
import com.haikal.athena.databinding.ActivityRegisterBinding
import com.haikal.athena.ui.ViewModelFactory
import com.haikal.athena.ui.auth.login.LoginActivity

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private val viewModel: RegisterViewModel by viewModels { ViewModelFactory.getInstance(this) }
    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    private val fields: List<View> by lazy {
        listOf(
            binding.ivRegister,
            binding.tvRegister,
            binding.nameEditTextLayout,
            binding.emailEditTextLayout,
            binding.passwordEditTextLayout,
            binding.btnRegister,
            binding.tvRegisterToLoginHint,
            binding.tvRegisterToLogin
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnRegister.setOnClickListener(this)
        binding.tvRegisterToLogin.setOnClickListener(this)
        configureObserver()

        enableFields(true)
        playAnimation(true)
    }

    private fun register(name: String, email: String, password: String) {
        viewModel.register(name, email, password)
    }

    override fun onClick(view: View) {
        val binding = binding
        val name = binding.edRegisterName.text.toString().trim()
        val email = binding.edRegisterEmail.text.toString().trim()
        val password = binding.edRegisterPassword.text.toString().trim()

        when (view.id) {
            binding.btnRegister.id -> {
                if (!viewModel.registerIsValid(binding)) {
                    Toast.makeText(
                        this@RegisterActivity,
                        getString(R.string.register_invalid_input_toast),
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }
                register(name, email, password)
            }

            binding.tvRegisterToLogin.id -> {
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                startActivity(intent)
            }
        }
    }

    private fun playAnimation(visible: Boolean, duration: Long = 750) {
        val targetValue = if (visible) 1f else 0f

        val animations = fields.map {
            ObjectAnimator.ofFloat(it, View.ALPHA, targetValue).setDuration(duration)
        }

        var it = 0
        val logoAnim = animations[it++]
        val titleAnim = animations[it++]
        val nameAnim = animations[it++]
        val emailAnim = animations[it++]
        val passwdAnim = animations[it++]
        val btnRegisAnim = animations[it++]
        val btnLoginHintAnim = animations[it++]
        val btnLoginAnim = animations[it]

        AnimatorSet().apply {
            playSequentially(
                logoAnim,
                titleAnim,
                nameAnim,
                emailAnim,
                passwdAnim,
                btnRegisAnim,
                btnLoginHintAnim,
                btnLoginAnim
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

    private fun configureObserver() {
        viewModel.registerStatus.observe(this) { result ->
            when (result) {
                is RequestInformation.Loading -> {
                    playAnimation(false, 200)
                    showLoading(true)
                    enableFields(false)
                }

                is RequestInformation.Error -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        getString(R.string.register_failed_toast, result.error),
                        Toast.LENGTH_SHORT
                    ).show()
                    playAnimation(true, 200)
                    showLoading(false)
                    enableFields(true)
                }

                is RequestInformation.Success -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        getString(R.string.register_success_toast),
                        Toast.LENGTH_SHORT
                    ).show()
                    playAnimation(true, 200)

                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
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