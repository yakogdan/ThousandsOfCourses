package com.yakogdan.thousandsofcourses.di.modules

import androidx.lifecycle.ViewModelProvider
import com.yakogdan.thousandsofcourses.di.factories.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}