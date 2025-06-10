package com.yakogdan.thousandsofcourses.domain.repositories

import com.yakogdan.thousandsofcourses.domain.models.CourseModel

interface CourseRepository {

    suspend fun getCoursesFromApi(): List<CourseModel>

    suspend fun getFavoriteCourses(): List<CourseModel>

    suspend fun addCourseToFavorite(course: CourseModel)
}