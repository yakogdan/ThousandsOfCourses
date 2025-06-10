package com.yakogdan.thousandsofcourses.di.modules

import androidx.lifecycle.ViewModel
import com.yakogdan.thousandsofcourses.data.repositories.CourseRepoImpl
import com.yakogdan.thousandsofcourses.di.factories.ViewModelKey
import com.yakogdan.thousandsofcourses.di.scopes.FavoritesScope
import com.yakogdan.thousandsofcourses.domain.repositories.CourseRepository
import com.yakogdan.thousandsofcourses.presentation.screens.favorites.FavoritesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FavoritesModule {

    @[FavoritesScope Binds]
    fun bindCourseRepository(impl: CourseRepoImpl): CourseRepository

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    fun bindFavoritesViewModel(viewModel: FavoritesViewModel): ViewModel
}