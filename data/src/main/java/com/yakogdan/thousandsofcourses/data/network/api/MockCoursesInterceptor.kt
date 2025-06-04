package com.yakogdan.thousandsofcourses.data.network.api

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockCoursesInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (request.url.encodedPath == "/courses") {
            val mockJson = context.assets
                .open("courses.json")
                .bufferedReader()
                .use { it.readText() }

            return Response.Builder()
                .request(request)
                .code(200)
                .protocol(Protocol.HTTP_1_1)
                .message("OK")
                .body(mockJson.toByteArray().toResponseBody("application/json".toMediaType()))
                .build()
        }

        return chain.proceed(request)
    }
}