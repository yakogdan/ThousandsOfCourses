package com.yakogdan.thousandsofcourses.presentation.screens.favorites

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
import com.yakogdan.thousandsofcourses.databinding.FragmentFavoritesBinding
import com.yakogdan.thousandsofcourses.di.components.DaggerFavoritesComponent
import com.yakogdan.thousandsofcourses.presentation.activities.MainActivity
import com.yakogdan.thousandsofcourses.presentation.adapters.courses.CoursesAdapter
import com.yakogdan.thousandsofcourses.presentation.adapters.courses.course.CoursesAdapterDelegate
import com.yakogdan.thousandsofcourses.presentation.adapters.itemdecoration.CourseItemDecoration
import com.yakogdan.thousandsofcourses.presentation.navigation.Screens.Course
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FavoritesViewModel::class.java]
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
        val favoritesComponent =
            DaggerFavoritesComponent.builder().applicationComponent(applicationComponent).build()
        favoritesComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoritesBinding.bind(view)

        (activity as MainActivity).setBottomNavVisibility(isVisible = true)

        initFavoriteCoursesAdapter()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    processFavoritesScreenState()
                }
            }
        }
    }

    private suspend fun processFavoritesScreenState(): Nothing {
        viewModel.favoritesScreenState.collect {
            when (val screenState = it) {

                FavoritesScreenState.Empty -> {
                    Toast.makeText(
                        /* context = */ requireContext(),
                        /* text = */ getString(R.string.empty),
                        /* duration = */ Toast.LENGTH_SHORT
                    ).show()
                }

                is FavoritesScreenState.Error -> {
                    Toast.makeText(
                        /* context = */ requireContext(),
                        /* text = */ getString(
                            R.string.course_loading_error,
                            screenState.throwable.message
                        ),
                        /* duration = */ Toast.LENGTH_SHORT
                    ).show()
                }

                FavoritesScreenState.Loading -> {
                    Toast.makeText(
                        /* context = */ requireContext(),
                        /* text = */ getString(R.string.loading_courses),
                        /* duration = */ Toast.LENGTH_SHORT
                    ).show()
                }

                is FavoritesScreenState.Success -> {
                    coursesAdapter.submitList(it.courses)
                }

                FavoritesScreenState.Initial -> {}
            }
        }
    }

    private fun initFavoriteCoursesAdapter() {
        binding.apply {
            rvFavoriteCurses.adapter = coursesAdapter
            rvFavoriteCurses.addItemDecoration(
                CourseItemDecoration(
                    paddingBottomDp = 16,
                    context = requireContext(),
                )
            )
        }
        viewModel.loadFavoriteCurses()
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