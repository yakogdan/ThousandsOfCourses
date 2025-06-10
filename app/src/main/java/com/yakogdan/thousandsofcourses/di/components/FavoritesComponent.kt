package com.yakogdan.thousandsofcourses.di.components

import com.github.terrakok.cicerone.Router
import com.yakogdan.thousandsofcourses.data.database.AppDatabase
import com.yakogdan.thousandsofcourses.data.database.dao.CourseDao
import com.yakogdan.thousandsofcourses.di.modules.FavoritesModule
import com.yakogdan.thousandsofcourses.di.modules.ViewModelFactoryModule
import com.yakogdan.thousandsofcourses.di.scopes.FavoritesScope
import com.yakogdan.thousandsofcourses.presentation.screens.favorites.FavoritesFragment
import dagger.Component

@FavoritesScope
@Component(
    dependencies = [
        ApplicationComponent::class
    ],
    modules = [
        FavoritesModule::class,
        ViewModelFactoryModule::class,
    ],
)
interface FavoritesComponent {

    fun inject(fragment: FavoritesFragment)
    fun getRouter(): Router
    fun getAppDatabase(): AppDatabase
    fun getCourseDao(): CourseDao
}