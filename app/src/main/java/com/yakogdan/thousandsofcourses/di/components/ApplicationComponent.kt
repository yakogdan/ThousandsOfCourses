package com.yakogdan.thousandsofcourses.di.components

import android.app.Application
import com.github.terrakok.cicerone.Router
import com.yakogdan.thousandsofcourses.app.CoursesApp
import com.yakogdan.thousandsofcourses.di.modules.NavigationModule
import com.yakogdan.thousandsofcourses.di.scopes.ApplicationScope
import com.yakogdan.thousandsofcourses.presentation.activities.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        NavigationModule::class,
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(application: CoursesApp)
    fun getRouter(): Router

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}