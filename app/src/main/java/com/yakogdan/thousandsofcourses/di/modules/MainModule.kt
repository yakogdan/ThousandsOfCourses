package com.yakogdan.thousandsofcourses.di.modules

import com.yakogdan.thousandsofcourses.data.repositories.CourseRepoImpl
import com.yakogdan.thousandsofcourses.di.scopes.MainScope
import com.yakogdan.thousandsofcourses.domain.repositories.CourseRepository
import dagger.Binds
import dagger.Module

@Module
interface MainModule {

    @[MainScope Binds]
    fun bindCourseRepository(impl: CourseRepoImpl): CourseRepository
}