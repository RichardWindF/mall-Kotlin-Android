package com.example.mallkotlindemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.example.mall_library.global.GlobalKeys
import com.example.mall_library.global.Mall
import com.example.mall_library.global.net.RestClient
import com.example.mall_library.global.net.callback.ISuccess

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //这里如果想获取配置什么的,可以这样写：
        //Mall.getConfiguration<Context>(GlobalKeys.APPLICATION_CONTEXT)
        //******

        RestClient                       //比较舒服的方式网络请求，测试
            .builder()         //创建一个builder，然后依次传入参数
            .url("")
            .params("","")
            .success(object:ISuccess{                  //成功与否的回调，其它几个也可以回调
                override fun onSuccess(response: String) {
                    //TODO("Not yet implemented")
                }
            })     //当然也可以加入回调
            .build()           // 创建restClient
            .get()             //restClient 中的具体方法调用

        

    }
}