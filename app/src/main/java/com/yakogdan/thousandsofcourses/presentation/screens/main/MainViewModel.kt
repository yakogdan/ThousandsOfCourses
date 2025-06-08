package com.yakogdan.thousandsofcourses.presentation.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakogdan.thousandsofcourses.domain.models.CourseModel
import com.yakogdan.thousandsofcourses.domain.usecases.GetCoursesFromApiUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCoursesFromApiUseCase: GetCoursesFromApiUseCase,
) : ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("exceptionHandler", throwable.message.toString())
    }

    private val _coursesState =
        MutableStateFlow<List<CourseModel>>(emptyList())
    val coursesState: StateFlow<List<CourseModel>> = _coursesState.asStateFlow()

    fun loadCursesFromApi() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _coursesState.value = getCoursesFromApiUseCase.invoke()
        }
    }
}