package com.yakogdan.thousandsofcourses.di.modules

import android.app.Application
import com.yakogdan.thousandsofcourses.data.network.api.ApiService
import com.yakogdan.thousandsofcourses.data.network.api.createApiService
import com.yakogdan.thousandsofcourses.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @[ApplicationScope Provides]
    fun provideApiService(application: Application): ApiService =
        createApiService(context = application.applicationContext)
}