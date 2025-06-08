package com.yakogdan.thousandsofcourses.di.modules

import androidx.lifecycle.ViewModel
import com.yakogdan.thousandsofcourses.di.factories.ViewModelKey
import com.yakogdan.thousandsofcourses.presentation.screens.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LoginModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel
}