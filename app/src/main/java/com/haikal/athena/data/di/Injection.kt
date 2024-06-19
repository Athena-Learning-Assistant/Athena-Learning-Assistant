package com.haikal.athena.data.di

import android.content.Context
import com.haikal.athena.data.remote.retrofit.ApiConfig
import com.haikal.athena.data.repository.AuthRepository

object Injection {
    fun provideAuthRepository(context: Context): AuthRepository {
        val apiService = ApiConfig.getApiService()
        return AuthRepository.getInstance(apiService, context)
    }
}