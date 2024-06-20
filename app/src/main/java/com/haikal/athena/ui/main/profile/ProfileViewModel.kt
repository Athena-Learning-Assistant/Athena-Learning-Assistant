package com.haikal.athena.ui.main.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.haikal.athena.data.local.pref.SessionManager
import kotlinx.coroutines.launch

class ProfileViewModel(private val sessionManager: SessionManager) : ViewModel() {

    val authToken = sessionManager.authToken.asLiveData()

    // Fungsi untuk logout
    suspend fun logout() {
        sessionManager.clearAuthToken()
    }
}