package com.yakogdan.thousandsofcourses.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakogdan.thousandsofcourses.domain.models.CourseModel
import com.yakogdan.thousandsofcourses.domain.usecases.AddCourseToFavoriteUseCase
import com.yakogdan.thousandsofcourses.domain.usecases.GetCoursesFromApiUseCase
import com.yakogdan.thousandsofcourses.presentation.adapters.courses.course.toDelegates
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCoursesFromApiUseCase: GetCoursesFromApiUseCase,
    private val addCourseToFavoriteUseCase: AddCourseToFavoriteUseCase,
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _mainScreenState.value = MainScreenState.Error(throwable = throwable)
    }

    private val _mainScreenState =
        MutableStateFlow<MainScreenState>(MainScreenState.Initial)
    val mainScreenState: StateFlow<MainScreenState> = _mainScreenState.asStateFlow()

    fun loadCursesFromApi() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {

            _mainScreenState.value = MainScreenState.Loading

            val courses = getCoursesFromApiUseCase.invoke()

            if (courses.isEmpty()) {
                _mainScreenState.value = MainScreenState.Empty
            } else {
                _mainScreenState.value = MainScreenState.Success(courses = courses.toDelegates())
            }
        }
    }

    fun sortCoursesInDescendingOrder() {
        if (mainScreenState.value is MainScreenState.Success) {
            val courses = (mainScreenState.value as MainScreenState.Success).courses
            _mainScreenState.value =
                MainScreenState.Success(
                    courses = courses
                        .sortedByDescending {
                            (it.content() as CourseModel).publishDate
                        }
                )
        }
    }

    fun addCourseToFavorite(course: CourseModel) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            addCourseToFavoriteUseCase.invoke(course = course)
        }
    }
}