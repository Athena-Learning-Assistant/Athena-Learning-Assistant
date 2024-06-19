package com.haikal.athena.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.haikal.athena.data.di.Injection
import com.haikal.athena.data.local.pref.SessionManager
import com.haikal.athena.data.repository.AuthRepository
import com.haikal.athena.ui.auth.login.LoginViewModel
import com.haikal.athena.ui.auth.register.RegisterViewModel
import com.haikal.athena.ui.profile.ProfileViewModel

class ViewModelFactory private constructor(
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(authRepository)
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(authRepository)
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(sessionManager)
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
                    Injection.provideAuthRepository(context),
                    SessionManager(context)
                ).also { INSTANCE = it }
            }
    }
}