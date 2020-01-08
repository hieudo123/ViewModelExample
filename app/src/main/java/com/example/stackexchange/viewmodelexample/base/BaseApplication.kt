package com.example.stackexchange.viewmodelexample.base

import android.app.Application
import com.example.stackexchange.viewmodelexample.di.Injector
import com.example.stackexchange.viewmodelexample.di.component.ApplicationComponent
import com.example.stackexchange.viewmodelexample.di.component.DaggerApplicationComponent
import com.example.stackexchange.viewmodelexample.di.module.ApplicationModule


class BaseApplication : Application() {

    companion object{
        private var activityVisible: Boolean = false

        fun isActivityVisible(): Boolean {
            return activityVisible
        }
        fun activityResumed() {
            activityVisible = true
        }

        fun activityPaused() {
            activityVisible = false
        }
    }
    private var activityVisible: Boolean = false
    override fun onCreate() {
        super.onCreate()
        Injector.initialize(this)
        Injector.getApplicationComponent()!!.inject(this)
    }







}