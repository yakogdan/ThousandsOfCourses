package com.yakogdan.thousandsofcourses.app

import android.app.Application
import com.yakogdan.thousandsofcourses.di.components.DaggerApplicationComponent

class CoursesApp : Application() {

    val applicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        applicationComponent.inject(this)
        super.onCreate()
    }
}