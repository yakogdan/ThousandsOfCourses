package com.yakogdan.thousandsofcourses.presentation.adapters.courses.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yakogdan.thousandsofcourses.R
import com.yakogdan.thousandsofcourses.data.tools.toStringDate
import com.yakogdan.thousandsofcourses.databinding.CourseCardBinding
import com.yakogdan.thousandsofcourses.domain.models.CourseModel
import com.yakogdan.thousandsofcourses.presentation.adapters.delegate.AdapterDelegate
import com.yakogdan.thousandsofcourses.presentation.adapters.delegate.DelegateItem

class CoursesAdapterDelegate(
    private val onCourseClick: (course: CourseModel) -> Unit,
    private val onCourseLongClick: (course: CourseModel) -> Unit = { },

    ) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        StreamViewHolder(
            binding = CourseCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onCourseClick = onCourseClick,
            onCourseLongClick = onCourseLongClick,
        )

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        delegateItem: DelegateItem,
        position: Int
    ) {
        (holder as StreamViewHolder).bind(
            course = delegateItem.content() as CourseModel
        )
    }

    override fun isOfViewType(delegateItem: DelegateItem): Boolean =
        delegateItem is CourseDelegateItem

    class StreamViewHolder(
        private val binding: CourseCardBinding,
        private val onCourseClick: (course: CourseModel) -> Unit,
        private val onCourseLongClick: (course: CourseModel) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(course: CourseModel) {

            val favoriteImageRes =
                if (course.hasLike) R.drawable.ic_favorite else R.drawable.ic_not_favorite

            val price = "${course.price} ₽"

            binding.apply {
                root.apply {
                    setOnClickListener {
                        onCourseClick(course)
                    }
                    setOnLongClickListener {
                        onCourseLongClick(course)
                        true
                    }
                }
                ivCourseImage.setImageResource(R.drawable.course_image) // В данных из JSON нет ссылки на изображение
                btnIsFavorite.setImageResource(favoriteImageRes)
                tvRating.text = course.rate
                tvDate.text = course.startDate.toStringDate()
                tvTitle.text = course.title
                tvDescription.text = course.text
                tvPrice.text = price
            }
        }
    }
}