package com.example.stackexchange.viewmodelexample.di.module

import android.app.Application
import com.example.stackexchange.viewmodelexample.base.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(baseApplication: BaseApplication) {
    private var application: BaseApplication? = baseApplication

    @Provides
    @Singleton
    fun provideApplication(): Application? {
        return this.application
    }
}