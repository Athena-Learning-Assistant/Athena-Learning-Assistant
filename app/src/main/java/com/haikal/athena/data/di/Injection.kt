package com.haikal.athena.data.di

import com.haikal.athena.data.remote.retrofit.ApiConfig
import com.haikal.athena.data.repository.AuthRepository

object Injection {
    fun provideAuthRepository(): AuthRepository {
        val apiService = ApiConfig.getApiService()
        return AuthRepository.getInstance(apiService)
    }
}