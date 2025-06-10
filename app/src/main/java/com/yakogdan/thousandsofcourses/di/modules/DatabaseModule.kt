package com.yakogdan.thousandsofcourses.di.modules

import android.app.Application
import com.yakogdan.thousandsofcourses.data.database.AppDatabase
import com.yakogdan.thousandsofcourses.data.database.dao.CourseDao
import com.yakogdan.thousandsofcourses.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @[ApplicationScope Provides]
    fun provideAppDatabase(application: Application): AppDatabase {
        return AppDatabase.getInstance(application)
    }

    @[ApplicationScope Provides]
    fun provideCourseDao(appDatabase: AppDatabase): CourseDao =
        appDatabase.courseDao()
}