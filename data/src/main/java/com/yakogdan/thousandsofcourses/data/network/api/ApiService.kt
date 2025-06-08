package com.yakogdan.thousandsofcourses.data.network.api

import com.yakogdan.thousandsofcourses.data.network.dto.CoursesDTO
import retrofit2.http.GET

interface ApiService {

    @GET("/courses")
    suspend fun getCourses(): CoursesDTO

    companion object {
        const val BASE_URL = "https://example.local/"
    }
}