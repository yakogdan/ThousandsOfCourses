package com.yakogdan.thousandsofcourses.di.modules

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.yakogdan.thousandsofcourses.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @[ApplicationScope Provides]
    fun provideRouter(): Router {
        return cicerone.router
    }

    @[ApplicationScope Provides]
    fun provideNavigatorHolder(): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }
}