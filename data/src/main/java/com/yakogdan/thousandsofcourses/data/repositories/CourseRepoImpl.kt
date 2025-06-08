package com.yakogdan.thousandsofcourses.data.repositories

import com.yakogdan.thousandsofcourses.data.network.api.ApiService
import com.yakogdan.thousandsofcourses.data.network.mappers.toModels
import com.yakogdan.thousandsofcourses.domain.models.CourseModel
import com.yakogdan.thousandsofcourses.domain.repositories.CourseRepository
import javax.inject.Inject

class CourseRepoImpl @Inject constructor(
    private val apiService: ApiService,
) : CourseRepository {

    override suspend fun getCoursesFromApi(): List<CourseModel> =
        apiService.getCourses().courses.toModels()
}