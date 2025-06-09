package com.yakogdan.thousandsofcourses.presentation.screens.main

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.terrakok.cicerone.Router
import com.yakogdan.thousandsofcourses.R
import com.yakogdan.thousandsofcourses.app.CoursesApp
import com.yakogdan.thousandsofcourses.databinding.FragmentMainBinding
import com.yakogdan.thousandsofcourses.di.components.DaggerMainComponent
import com.yakogdan.thousandsofcourses.presentation.activities.MainActivity
import com.yakogdan.thousandsofcourses.presentation.adapters.courses.CoursesAdapter
import com.yakogdan.thousandsofcourses.presentation.adapters.courses.course.CoursesAdapterDelegate
import com.yakogdan.thousandsofcourses.presentation.adapters.itemdecoration.CourseItemDecoration
import com.yakogdan.thousandsofcourses.presentation.navigation.Screens.Course
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private val coursesAdapter: CoursesAdapter by lazy(LazyThreadSafetyMode.NONE) {
        CoursesAdapter().apply {
            addDelegates()
        }
    }

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

        initCoursesAdapter()

        binding.btnDateSorting.setOnClickListener {
            viewModel.sortCoursesInDescendingOrder()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    processMainScreenState()
                }
            }
        }
    }

    private suspend fun processMainScreenState(): Nothing {
        viewModel.mainScreenState.collect {
            when (val screenState = it) {

                MainScreenState.Empty -> {
                    Toast.makeText(
                        /* context = */ requireContext(),
                        /* text = */ getString(R.string.empty),
                        /* duration = */ Toast.LENGTH_SHORT
                    ).show()
                }

                is MainScreenState.Error -> {
                    Toast.makeText(
                        /* context = */ requireContext(),
                        /* text = */ getString(
                            R.string.course_loading_error,
                            screenState.throwable.message
                        ),
                        /* duration = */ Toast.LENGTH_SHORT
                    ).show()
                }

                MainScreenState.Loading -> {
                    Toast.makeText(
                        /* context = */ requireContext(),
                        /* text = */ getString(R.string.loading_courses),
                        /* duration = */ Toast.LENGTH_SHORT
                    ).show()
                }

                is MainScreenState.Success -> {
                    coursesAdapter.submitList(it.courses)
                }

                MainScreenState.Initial -> {}
            }
        }
    }

    private fun initCoursesAdapter() {
        binding.apply {
            rvCurses.adapter = coursesAdapter
            rvCurses.addItemDecoration(
                CourseItemDecoration(
                    paddingBottomDp = 16,
                    context = requireContext(),
                )
            )
        }
        viewModel.loadCursesFromApi()
    }

    private fun CoursesAdapter.addDelegates() {
        addDelegate(
            CoursesAdapterDelegate(
                onCourseClick = { course ->
                    router.navigateTo(Course())
                }
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}