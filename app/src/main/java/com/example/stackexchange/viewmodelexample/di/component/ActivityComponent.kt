package com.example.stackexchange.viewmodelexample.di.component

import android.app.Activity
import android.app.Application
import com.example.stackexchange.viewmodelexample.base.BaseActivity
import com.example.stackexchange.viewmodelexample.di.module.ActivityModule
import com.example.stackexchange.viewmodelexample.di.module.NetworkModule
import com.example.stackexchange.viewmodelexample.view.news.NewsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@ActivityScope
@Component(dependencies = [ApplicationComponent::class],modules = [ActivityModule::class, NetworkModule::class])
interface ActivityComponent {
    fun inject(baseActivity: BaseActivity)
    fun inject(newsFragment: NewsFragment)

}