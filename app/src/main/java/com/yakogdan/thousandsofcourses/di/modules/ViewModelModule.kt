package com.yakogdan.thousandsofcourses.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yakogdan.thousandsofcourses.di.factories.ViewModelFactory
import com.yakogdan.thousandsofcourses.di.factories.ViewModelKey
import com.yakogdan.thousandsofcourses.presentation.screens.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}