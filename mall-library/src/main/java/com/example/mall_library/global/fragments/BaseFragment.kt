package com.example.mall_library.global.fragments

import android.os.Bundle
import android.support.annotation.IdRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.yokeyword.fragmentation.SupportFragment    //第三方库引用
import java.lang.ClassCastException

abstract class BaseFragment: SupportFragment()
{
    private lateinit var mRootView: View //=null --红线要处理,添加lateinit 关键字

    abstract fun setLayout():Any                 //初始的几个抽象方法

    abstract fun onBindView(savedInstanceState:Bundle?,rootView: View)

    fun<T:View> findView(@IdRes viewId: Int):T         //这里封装为了简便，不一定非要如此
    {
        //if (mRootView != null) return mRootView?.findViewById(viewId)!!  //尽量不出现双感叹号
        //throw NullPointerException("rootView is null")
        return mRootView.findViewById(viewId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        val rootView=when{
            //从xml文件中取出来的
            setLayout() is Int->inflater.inflate(setLayout()as Int,container,false)
            //new 出来的view
            setLayout() is View->setLayout() as View
            else->throw ClassCastException("type of setLayout() must be int or view")
        }

        mRootView=rootView
        onBindView(savedInstanceState,rootView)
        return rootView

    }
}


