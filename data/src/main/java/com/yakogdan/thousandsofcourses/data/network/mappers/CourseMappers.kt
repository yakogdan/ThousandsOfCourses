package com.yakogdan.thousandsofcourses.data.network.mappers

import com.yakogdan.thousandsofcourses.data.network.dto.CourseDTO
import com.yakogdan.thousandsofcourses.domain.models.CourseModel

fun CourseDTO.toModel() = CourseModel(
    id = id,
    hasLike = hasLike,
    price = price,
    publishDate = publishDate,
    rate = rate,
    startDate = startDate,
    text = text,
    title = title,
)

fun List<CourseDTO>.toModels() = map { it.toModel() }