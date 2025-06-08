package com.yakogdan.thousandsofcourses.di.modules

import androidx.lifecycle.ViewModel
import com.yakogdan.thousandsofcourses.data.repositories.CourseRepoImpl
import com.yakogdan.thousandsofcourses.di.factories.ViewModelKey
import com.yakogdan.thousandsofcourses.di.scopes.MainScope
import com.yakogdan.thousandsofcourses.domain.repositories.CourseRepository
import com.yakogdan.thousandsofcourses.presentation.screens.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @[MainScope Binds]
    fun bindCourseRepository(impl: CourseRepoImpl): CourseRepository

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}