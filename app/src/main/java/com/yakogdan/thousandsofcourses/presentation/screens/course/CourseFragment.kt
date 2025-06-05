package com.yakogdan.thousandsofcourses.presentation.screens.course

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.yakogdan.thousandsofcourses.R
import com.yakogdan.thousandsofcourses.databinding.FragmentCourseBinding

class CourseFragment : Fragment(R.layout.fragment_course) {

    private var _binding: FragmentCourseBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCourseBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}