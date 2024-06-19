package com.haikal.athena.ui.auth.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.haikal.athena.data.repository.AuthRepository
import com.haikal.athena.databinding.ActivityRegisterBinding

class RegisterViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _registerInput = MutableLiveData<Triple<String, String, String>>()

    val registerStatus = _registerInput.switchMap {
        authRepository.register(it.first, it.second, it.third)
    }

    fun register(fullName: String, email: String, password: String) {
        _registerInput.value = Triple(fullName, email, password)
    }

    fun registerIsValid(binding: ActivityRegisterBinding): Boolean {
        val isErrorFree = listOf(
            binding.edRegisterName.error,
            binding.edRegisterEmail.error,
            binding.edRegisterPassword.error
        ).all { it == null }

        val isNonEmpty = listOf(
            binding.edRegisterName.text.toString(),
            binding.edRegisterEmail.text.toString(),
            binding.edRegisterPassword.text.toString()
        ).all { it.isNotEmpty() }

        return isErrorFree && isNonEmpty
    }
}