package com.example.stackexchange.viewmodelexample.base

import android.app.Application

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
    }






}