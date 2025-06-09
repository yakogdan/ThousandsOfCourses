package com.yakogdan.thousandsofcourses.data.network.mappers

import com.yakogdan.thousandsofcourses.data.network.dto.CourseDTO
import com.yakogdan.thousandsofcourses.data.tools.toDate
import com.yakogdan.thousandsofcourses.domain.models.CourseModel

fun CourseDTO.toModel() = CourseModel(
    id = id,
    hasLike = hasLike,
    price = price,
    publishDate = publishDate.toDate(),
    rate = rate,
    startDate = startDate.toDate(),
    text = text,
    title = title,
)

fun List<CourseDTO>.toModels() = map { it.toModel() }