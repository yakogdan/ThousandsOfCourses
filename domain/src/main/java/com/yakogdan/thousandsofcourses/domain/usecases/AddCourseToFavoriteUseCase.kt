package com.yakogdan.thousandsofcourses.domain.usecases

import com.yakogdan.thousandsofcourses.domain.models.CourseModel
import com.yakogdan.thousandsofcourses.domain.repositories.CourseRepository
import javax.inject.Inject

class AddCourseToFavoriteUseCase @Inject constructor(
    private val repository: CourseRepository,
) {

    suspend fun invoke(course: CourseModel) {
        repository.addCourseToFavorite(course = course)
    }
}