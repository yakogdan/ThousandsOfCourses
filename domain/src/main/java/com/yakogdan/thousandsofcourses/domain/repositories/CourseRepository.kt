package com.yakogdan.thousandsofcourses.domain.repositories

import com.yakogdan.thousandsofcourses.domain.models.CourseModel

interface CourseRepository {

    suspend fun getCoursesFromApi(): List<CourseModel>
}