package com.haikal.athena.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.haikal.athena.data.RequestInformation
import com.haikal.athena.data.local.pref.SessionManager
import com.haikal.athena.data.local.pref.UserModel
import com.haikal.athena.data.remote.response.ErrorResponse
import com.haikal.athena.data.remote.retrofit.ApiService
import retrofit2.HttpException

class AuthRepository private constructor(
    private val apiService: ApiService,
    private val context: Context
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
            val token = response.token
            if (token.isNullOrEmpty()) throw Exception("Token is null or empty")
            val user = UserModel("User Name", email, "User ID", token)

            // Simpan token ke dalam DataStore
            SessionManager(context).saveAuthToken(token)

            emitSource(MutableLiveData(RequestInformation.Success(user)))
        } catch (e: HttpException) {
            val errorMessage = getErrorMessage(e)
            Log.e("AuthRepository", "HttpException: $errorMessage")
            emit(RequestInformation.Error(errorMessage))
        } catch (e: Exception) {
            Log.e("AuthRepository", "Exception: ${e.message.toString()}")
            emit(RequestInformation.Error(e.message.toString()))
        }
    }

    private fun getErrorMessage(e: HttpException): String {
        val jsonInString = e.response()?.errorBody()?.string()
        val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
        return errorBody.message ?: "Unknown error occurred"
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: AuthRepository? = null

        fun getInstance(apiService: ApiService, context: Context): AuthRepository =
            INSTANCE ?: synchronized(this) {
                AuthRepository(apiService, context).also { INSTANCE = it }
            }
    }
}