package com.haikal.athena.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.haikal.athena.data.di.Injection
import com.haikal.athena.data.repository.AuthRepository
import com.haikal.athena.ui.auth.register.RegisterViewModel

class ViewModelFactory private constructor(
    private val authRepository: AuthRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(authRepository)
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        } as T
    }

    companion object {
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory =
            INSTANCE ?: synchronized(this) {
                ViewModelFactory(
                    Injection.provideAuthRepository()
                ).also { INSTANCE = it }
            }
    }
}