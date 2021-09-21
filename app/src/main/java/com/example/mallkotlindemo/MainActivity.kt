package com.example.mallkotlindemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.example.mall_library.global.GlobalKeys
import com.example.mall_library.global.Mall

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //这里如果想获取配置什么的,可以这样写：
        //Mall.getConfiguration<Context>(GlobalKeys.APPLICATION_CONTEXT)

        //******
        

    }
}