package com.example.stackexchange.viewmodelexample.base

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.example.stackexchange.viewmodelexample.R
import com.example.stackexchange.viewmodelexample.di.Injector
import com.example.stackexchange.viewmodelexample.di.component.ActivityComponent
import com.example.stackexchange.viewmodelexample.view.MainActivity
import com.kaopiz.kprogresshud.KProgressHUD


abstract class BaseActivity : AppCompatActivity() {

    protected abstract fun  getLayoutId():Int

    protected abstract fun initView()

    private var mProgresDialog: KProgressHUD? = null

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.initialize(this)
        setContentView(getLayoutId())


        initView()
    }

    fun  getActivityComponent() : ActivityComponent { return Injector.getActivityComponent()!! }

    fun showLoading(){
        if (mProgresDialog != null)
            mProgresDialog!!.show()
        else{
            mProgresDialog = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show()
        }
    }

    fun hideLoading(){
        mProgresDialog?.dismiss()
    }

    override fun onResume() {
        super.onResume()
        BaseApplication.activityResumed()
    }

    override fun onPause() {
        super.onPause()
        BaseApplication.activityPaused()
    }

    open fun addFragment(fragment: BaseFragment?, isAddToBackStack: Boolean) {
        addReplaceFragment(fragment, false, isAddToBackStack)
    }

    open fun replaceFragment(fragment: BaseFragment?, isAddToBackStack: Boolean) {
        addReplaceFragment( fragment, true, isAddToBackStack)
    }

    open fun showActionbar(view: View, title: String?) {
        val tvTitle = view.findViewById<TextView>(R.id.actionbar_tvTitle)
        if (tvTitle != null) {
            tvTitle.text = title
        }
    }

    private fun addReplaceFragment(fragment: BaseFragment?, isReplace: Boolean, isAddToBackStack: Boolean) {
        val fragmentManager = supportFragmentManager
        if (fragment != null) {
            val fragmentTransaction =
                fragmentManager.beginTransaction()
            if (isReplace) fragmentTransaction.replace(
                if (this is MainActivity) R.id.frmContainer else R.id.frmContainer,
                fragment
            ) else {
                val currentFragment =
                    supportFragmentManager.findFragmentById(if (this is MainActivity) R.id.frmContainer else R.id.frmContainer)
                if (currentFragment != null) {
                    fragmentTransaction.hide(currentFragment)
                }
                fragmentTransaction.add(
                     if (this is MainActivity) R.id.frmContainer else R.id.frmContainer,
                    fragment,
                    fragment.javaClass.simpleName
                )
            }
            if (isAddToBackStack) {
                fragmentTransaction.addToBackStack(fragment.javaClass.getSimpleName())
            }
            if (BaseApplication.isActivityVisible()) {
                fragmentTransaction.commit()
            } else fragmentTransaction.commitAllowingStateLoss()
        }
    }
}