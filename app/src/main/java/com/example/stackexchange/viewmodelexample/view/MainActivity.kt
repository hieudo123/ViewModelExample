package com.example.stackexchange.viewmodelexample.view

import android.os.Build
import androidx.core.content.ContextCompat
import com.example.stackexchange.viewmodelexample.R
import com.example.stackexchange.viewmodelexample.base.BaseActivity
import com.example.stackexchange.viewmodelexample.view.news.NewsFragment

class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        replaceFragment(NewsFragment(),false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.actionbar_color)
        }
    }


}
