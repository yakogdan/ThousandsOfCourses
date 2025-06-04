package com.yakogdan.thousandsofcourses.data.network.api

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

fun createApiService(context: Context): ApiService {
    val json = Json { ignoreUnknownKeys = true }

    val client = OkHttpClient.Builder()
        .addInterceptor(MockCoursesInterceptor(context.applicationContext))
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(ApiService.BASE_URL)
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    return retrofit.create(ApiService::class.java)
}