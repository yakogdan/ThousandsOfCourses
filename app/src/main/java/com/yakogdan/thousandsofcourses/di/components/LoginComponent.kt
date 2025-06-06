package com.yakogdan.thousandsofcourses.di.components

import com.github.terrakok.cicerone.Router
import com.yakogdan.thousandsofcourses.di.scopes.LoginScope
import com.yakogdan.thousandsofcourses.presentation.screens.login.LoginFragment
import dagger.Component

@LoginScope
@Component(
    dependencies = [
        ApplicationComponent::class
    ]
)
interface LoginComponent {

    fun inject(fragment: LoginFragment)
    fun getRouter(): Router
}