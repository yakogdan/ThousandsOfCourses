package com.yakogdan.thousandsofcourses.di.components

import android.app.Application
import com.github.terrakok.cicerone.Router
import com.yakogdan.thousandsofcourses.app.CoursesApp
import com.yakogdan.thousandsofcourses.data.database.AppDatabase
import com.yakogdan.thousandsofcourses.data.database.dao.CourseDao
import com.yakogdan.thousandsofcourses.data.network.api.ApiService
import com.yakogdan.thousandsofcourses.di.modules.DatabaseModule
import com.yakogdan.thousandsofcourses.di.modules.NavigationModule
import com.yakogdan.thousandsofcourses.di.modules.NetworkModule
import com.yakogdan.thousandsofcourses.di.scopes.ApplicationScope
import com.yakogdan.thousandsofcourses.presentation.activities.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        NavigationModule::class,
        NetworkModule::class,
        DatabaseModule::class,
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(application: CoursesApp)
    fun getRouter(): Router
    fun getApiService(): ApiService
    fun getAppDatabase(): AppDatabase
    fun getCourseDao(): CourseDao

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}