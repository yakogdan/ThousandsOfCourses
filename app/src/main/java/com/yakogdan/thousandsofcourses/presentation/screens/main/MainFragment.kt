package com.yakogdan.thousandsofcourses.presentation.screens.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import com.yakogdan.thousandsofcourses.R
import com.yakogdan.thousandsofcourses.app.CoursesApp
import com.yakogdan.thousandsofcourses.databinding.FragmentMainBinding
import com.yakogdan.thousandsofcourses.di.components.DaggerMainComponent
import com.yakogdan.thousandsofcourses.presentation.activities.MainActivity
import com.yakogdan.thousandsofcourses.presentation.navigation.Screens.Course
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var router: Router

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val applicationComponent =
            (requireContext().applicationContext as CoursesApp).applicationComponent
        val mainComponent =
            DaggerMainComponent.builder().applicationComponent(applicationComponent).build()
        mainComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        (activity as MainActivity).setBottomNavVisibility(isVisible = true)

        binding.btnMain.setOnClickListener {
            router.navigateTo(Course())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}