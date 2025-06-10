package com.yakogdan.thousandsofcourses.data.database.mappers

import com.yakogdan.thousandsofcourses.data.database.models.CourseDBO
import com.yakogdan.thousandsofcourses.domain.models.CourseModel

private fun CourseDBO.toModel(): CourseModel =
    CourseModel(
        id = id,
        hasLike = true,
        price = price,
        publishDate = publishDate,
        rate = rate,
        startDate = startDate,
        text = text,
        title = title,
    )

fun List<CourseDBO>.toModels(): List<CourseModel> =
    map { it.toModel() }

fun CourseModel.toDBO(): CourseDBO =
    CourseDBO(
        id = id,
        price = price,
        publishDate = publishDate,
        rate = rate,
        startDate = startDate,
        text = text,
        title = title,
    )