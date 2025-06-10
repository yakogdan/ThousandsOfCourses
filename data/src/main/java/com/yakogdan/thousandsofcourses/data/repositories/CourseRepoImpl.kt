package com.yakogdan.thousandsofcourses.data.repositories

import com.yakogdan.thousandsofcourses.data.database.dao.CourseDao
import com.yakogdan.thousandsofcourses.data.database.mappers.toDBO
import com.yakogdan.thousandsofcourses.data.database.mappers.toModels
import com.yakogdan.thousandsofcourses.data.network.api.ApiService
import com.yakogdan.thousandsofcourses.data.network.mappers.toModels
import com.yakogdan.thousandsofcourses.domain.models.CourseModel
import com.yakogdan.thousandsofcourses.domain.repositories.CourseRepository
import javax.inject.Inject

class CourseRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val courseDao: CourseDao,
) : CourseRepository {

    override suspend fun getCoursesFromApi(): List<CourseModel> =
        apiService.getCourses().courses.toModels()

    override suspend fun getFavoriteCourses(): List<CourseModel> =
        courseDao.getAllCourses().toModels()

    override suspend fun addCourseToFavorite(course: CourseModel) {
        courseDao.addCourse(course = course.toDBO())
    }
}