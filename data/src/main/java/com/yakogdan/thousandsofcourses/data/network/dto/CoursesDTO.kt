package com.yakogdan.thousandsofcourses.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoursesDTO(
    @SerialName("courses")
    val courses: List<CourseDTO>,
)