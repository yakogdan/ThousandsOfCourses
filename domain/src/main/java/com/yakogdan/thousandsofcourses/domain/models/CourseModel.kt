package com.yakogdan.thousandsofcourses.domain.models

data class CourseModel(
    val id: Int,
    val hasLike: Boolean,
    val price: String,
    val publishDate: String,
    val rate: String,
    val startDate: String,
    val text: String,
    val title: String,
)