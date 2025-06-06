package com.yakogdan.thousandsofcourses.di.components

import com.github.terrakok.cicerone.Router
import com.yakogdan.thousandsofcourses.di.scopes.MainScope
import com.yakogdan.thousandsofcourses.presentation.screens.main.MainFragment
import dagger.Component

@MainScope
@Component(
    dependencies = [
        ApplicationComponent::class
    ]
)
interface MainComponent {

    fun inject(fragment: MainFragment)
    fun getRouter(): Router
}