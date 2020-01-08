package com.example.stackexchange.viewmodelexample.di.component

import android.app.Application
import com.example.stackexchange.viewmodelexample.base.BaseApplication
import com.example.stackexchange.viewmodelexample.di.module.ApplicationModule
import com.example.stackexchange.viewmodelexample.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@AppScope
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(baseApplication: BaseApplication)

}