package com.example.stackexchange.viewmodelexample.di

import com.example.stackexchange.viewmodelexample.base.BaseActivity
import com.example.stackexchange.viewmodelexample.base.BaseApplication
import com.example.stackexchange.viewmodelexample.di.component.ActivityComponent
import com.example.stackexchange.viewmodelexample.di.component.ApplicationComponent
import com.example.stackexchange.viewmodelexample.di.component.DaggerActivityComponent
import com.example.stackexchange.viewmodelexample.di.component.DaggerApplicationComponent
import com.example.stackexchange.viewmodelexample.di.module.ActivityModule
import com.example.stackexchange.viewmodelexample.di.module.ApplicationModule
import com.example.stackexchange.viewmodelexample.di.module.NetworkModule

object Injector {

    private var applicationComponent: ApplicationComponent? = null

    private var activityComponent: ActivityComponent? = null


    fun initialize(baseApplication: BaseApplication?) {
        val applicationComponent: ApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(baseApplication?.let { ApplicationModule(it) })
            .build()
        this.applicationComponent = applicationComponent
    }

    fun initialize(baseActivity: BaseActivity) {
        val activityComponent: ActivityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(baseActivity))
            .applicationComponent(getApplicationComponent())
            .networkModule(NetworkModule())
            .build()
        this.activityComponent = activityComponent
    }

    fun getApplicationComponent(): ApplicationComponent? {
        return this.applicationComponent
    }

    fun getActivityComponent(): ActivityComponent? {
        return this.activityComponent
    }
}