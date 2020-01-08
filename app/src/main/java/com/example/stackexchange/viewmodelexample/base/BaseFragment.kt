package com.example.stackexchange.viewmodelexample.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.stackexchange.viewmodelexample.di.Injector
import com.example.stackexchange.viewmodelexample.di.component.ActivityComponent

abstract class BaseFragment : Fragment() {

    protected  abstract fun getLayoutId():Int

    protected  abstract fun initView(view : View)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(getLayoutId(), container, false)
        initView(view)
        return view
    }

    fun  getActivityComponent() : ActivityComponent { return Injector.getActivityComponent()!! }

    open fun addFragment(fragment: BaseFragment?, isAddToBackStack: Boolean) {
        if (activity is BaseActivity) {
            (activity as BaseActivity?)!!.addFragment(fragment, isAddToBackStack)
        }
    }

    open fun replaceFragment(fragment: BaseFragment?, isAddToBackStack: Boolean
    ) {
        if (activity is BaseActivity) {
            (activity as BaseActivity?)!!.replaceFragment(
                fragment,
                isAddToBackStack
            )
        }
    }

    open fun showActionbar(view: View, title: String?){
        if(activity is BaseActivity){
            (activity as BaseActivity).showActionbar(view,title)
        }
    }

    fun showLoading(){
       if (activity is BaseActivity){
           (activity as BaseActivity).showLoading()
       }
    }

    fun hideLoading(){
        if (activity is BaseActivity){
            (activity as BaseActivity).hideLoading()
        }
    }
}