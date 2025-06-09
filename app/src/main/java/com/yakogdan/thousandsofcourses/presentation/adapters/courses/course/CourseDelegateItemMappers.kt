package com.yakogdan.thousandsofcourses.presentation.adapters.courses.course

import com.yakogdan.thousandsofcourses.domain.models.CourseModel

fun CourseModel.toDelegate(): CourseDelegateItem =
    CourseDelegateItem(id = id, value = this)

fun List<CourseModel>.toDelegates(): List<CourseDelegateItem> =
    map { it.toDelegate() }