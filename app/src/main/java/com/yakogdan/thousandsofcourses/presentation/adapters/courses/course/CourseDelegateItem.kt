package com.yakogdan.thousandsofcourses.presentation.adapters.courses.course

import com.yakogdan.thousandsofcourses.domain.models.CourseModel
import com.yakogdan.thousandsofcourses.presentation.adapters.delegate.DelegateItem

class CourseDelegateItem(
    val id: Int,
    private val value: CourseModel,
) : DelegateItem {

    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: DelegateItem): Boolean =
        (other as CourseDelegateItem).value == content()
}