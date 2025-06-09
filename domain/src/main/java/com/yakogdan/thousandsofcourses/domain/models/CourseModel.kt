package com.yakogdan.thousandsofcourses.domain.models

import java.util.Date

data class CourseModel(
    val id: Int,
    val hasLike: Boolean,
    val price: String,
    val publishDate: Date,
    val rate: String,
    val startDate: Date,
    val text: String,
    val title: String,
)