package com.haikal.athena.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.haikal.athena.data.RequestInformation
import com.haikal.athena.data.local.pref.UserModel
import com.haikal.athena.data.remote.response.ErrorResponse
import com.haikal.athena.data.remote.retrofit.ApiService
import retrofit2.HttpException

class AuthRepository private constructor(
    private val apiService: ApiService,
) {
    fun register(
        name: String,
        email: String,
        password: String
    ): LiveData<RequestInformation<String>> = liveData {
        emit(RequestInformation.Loading)
        try {
            val response = apiService.register(name, email, password)
            if (response.error == true) throw Exception(response.message)
            val message = response.message ?: "Registration successful"
            emitSource(MutableLiveData(RequestInformation.Success(message)))
        } catch (e: HttpException) {
            val errorMessage = getErrorMessage(e)
            emit(RequestInformation.Error(errorMessage))
        } catch (e: Exception) {
            emit(RequestInformation.Error(e.message.toString()))
        }
    }

    fun login(email: String, password: String): LiveData<RequestInformation<UserModel>> = liveData {
        emit(RequestInformation.Loading)
        try {
            val response = apiService.login(email, password)
            if (response.error == true || response.loginResult == null) throw Exception(response.message)
            val data = response.loginResult
            val user = UserModel(data.name ?: "", email, data.userId ?: "", data.token ?: "")
            emitSource(MutableLiveData(RequestInformation.Success(user)))
        } catch (e: HttpException) {
            val errorMessage = getErrorMessage(e)
            emit(RequestInformation.Error(errorMessage))
        } catch (e: Exception) {
            emit(RequestInformation.Error(e.message.toString()))
        }

    }

    private fun getErrorMessage(e: HttpException): String {
        val jsonInString = e.response()?.errorBody()?.string()
        val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
        return errorBody.message ?: "Unknown error occurred"
    }

    companion object {
        @Volatile
        private var INSTANCE: AuthRepository? = null

        fun getInstance(apiService: ApiService): AuthRepository =
            INSTANCE ?: synchronized(this) {
                AuthRepository(apiService).also { INSTANCE = it }
            }
    }
}