package com.yakogdan.thousandsofcourses.presentation.screens.main

import com.yakogdan.thousandsofcourses.presentation.adapters.delegate.DelegateItem

sealed interface MainScreenState {

    data object Initial : MainScreenState

    data object Loading : MainScreenState

    data class Error(val throwable: Throwable) : MainScreenState

    data object Empty : MainScreenState

    data class Success(val courses: List<DelegateItem>) : MainScreenState
}