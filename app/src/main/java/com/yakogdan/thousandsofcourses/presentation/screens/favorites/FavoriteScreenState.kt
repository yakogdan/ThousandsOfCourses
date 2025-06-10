package com.yakogdan.thousandsofcourses.presentation.screens.favorites

import com.yakogdan.thousandsofcourses.presentation.adapters.delegate.DelegateItem

sealed interface FavoriteScreenState {

    data object Initial : FavoriteScreenState

    data object Loading : FavoriteScreenState

    data class Error(val throwable: Throwable) : FavoriteScreenState

    data object Empty : FavoriteScreenState

    data class Success(val courses: List<DelegateItem>) : FavoriteScreenState
}