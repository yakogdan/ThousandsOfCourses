package com.yakogdan.thousandsofcourses.presentation.screens.favorites

import com.yakogdan.thousandsofcourses.presentation.adapters.delegate.DelegateItem

sealed interface FavoritesScreenState {

    data object Initial : FavoritesScreenState

    data object Loading : FavoritesScreenState

    data class Error(val throwable: Throwable) : FavoritesScreenState

    data object Empty : FavoritesScreenState

    data class Success(val courses: List<DelegateItem>) : FavoritesScreenState
}