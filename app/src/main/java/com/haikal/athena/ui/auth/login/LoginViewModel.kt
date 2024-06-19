package com.haikal.athena.ui.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.haikal.athena.data.repository.AuthRepository
import com.haikal.athena.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val loginInput = MutableLiveData<Pair<String, String>>()

    val loginStatus = loginInput.switchMap {
        authRepository.login(it.first, it.second)
    }

    fun login(email: String, password: String) {
        loginInput.value = Pair(email, password)
    }

    fun loginIsValid(binding: ActivityLoginBinding): Boolean {
        val isErrorFree = listOf(
            binding.edLoginEmail.error,
            binding.edLoginPassword.error
        ).all { it == null }

        val isNonEmpty = listOf(
            binding.edLoginEmail.text.toString(),
            binding.edLoginPassword.text.toString()
        ).all { it.isNotEmpty() }

        return isErrorFree && isNonEmpty
    }
}