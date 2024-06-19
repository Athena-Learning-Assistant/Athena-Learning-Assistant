package com.haikal.athena.data.remote.retrofit

import com.haikal.athena.data.remote.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("auth/register")
    suspend fun register(
        @Field("fullName") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): RegisterResponse
}