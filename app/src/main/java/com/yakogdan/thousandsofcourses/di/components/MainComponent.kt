package com.yakogdan.thousandsofcourses.di.components

import com.github.terrakok.cicerone.Router
import com.yakogdan.thousandsofcourses.data.network.api.ApiService
import com.yakogdan.thousandsofcourses.di.modules.MainModule
import com.yakogdan.thousandsofcourses.di.modules.ViewModelModule
import com.yakogdan.thousandsofcourses.di.scopes.MainScope
import com.yakogdan.thousandsofcourses.presentation.screens.main.MainFragment
import dagger.Component

@MainScope
@Component(
    dependencies = [
        ApplicationComponent::class
    ],
    modules = [
        MainModule::class,
        ViewModelModule::class,
    ],
)
interface MainComponent {

    fun inject(fragment: MainFragment)
    fun getRouter(): Router
    fun getApiService(): ApiService
}