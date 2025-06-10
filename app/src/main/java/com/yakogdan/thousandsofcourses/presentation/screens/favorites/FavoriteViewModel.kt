package com.yakogdan.thousandsofcourses.presentation.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakogdan.thousandsofcourses.domain.models.CourseModel
import com.yakogdan.thousandsofcourses.domain.usecases.AddCourseToFavoriteUseCase
import com.yakogdan.thousandsofcourses.domain.usecases.GetFavoriteCoursesUseCase
import com.yakogdan.thousandsofcourses.presentation.adapters.courses.course.toDelegates
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val getFavoriteCoursesUseCase: GetFavoriteCoursesUseCase,
    private val addCourseToFavoriteUseCase: AddCourseToFavoriteUseCase,
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _favoriteScreenState.value = FavoriteScreenState.Error(throwable = throwable)
    }

    private val _favoriteScreenState =
        MutableStateFlow<FavoriteScreenState>(FavoriteScreenState.Initial)
    val favoriteScreenState: StateFlow<FavoriteScreenState> = _favoriteScreenState.asStateFlow()

    fun loadFavoriteCurses() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {

            _favoriteScreenState.value = FavoriteScreenState.Loading

            val courses = getFavoriteCoursesUseCase.invoke()

            if (courses.isEmpty()) {
                _favoriteScreenState.value = FavoriteScreenState.Empty
            } else {
                _favoriteScreenState.value =
                    FavoriteScreenState.Success(courses = courses.toDelegates())
            }
        }
    }

    fun addCourseToFavorite(course: CourseModel) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            addCourseToFavoriteUseCase.invoke(course = course)
        }
    }
}