package com.yakogdan.thousandsofcourses.domain.usecases

import com.yakogdan.thousandsofcourses.domain.models.CourseModel
import com.yakogdan.thousandsofcourses.domain.repositories.CourseRepository
import javax.inject.Inject

class GetFavoriteCoursesUseCase @Inject constructor(
    private val repository: CourseRepository,
) {

    suspend fun invoke(): List<CourseModel> =
        repository.getFavoriteCourses()
}