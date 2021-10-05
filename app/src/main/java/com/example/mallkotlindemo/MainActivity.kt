package com.example.mallkotlindemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import com.example.mall_library.global.GlobalKeys
import com.example.mall_library.global.Mall
import com.example.mall_library.global.net.RestClient
import com.example.mall_library.global.net.callback.IFailure
import com.example.mall_library.global.net.callback.ISuccess
import com.example.mall_library.global.ui.loader.LoaderStyles

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //这里如果想获取配置什么的,可以这样写：
        //Mall.getConfiguration<Context>(GlobalKeys.APPLICATION_CONTEXT)
        //******

        RestClient                       //比较舒服的方式网络请求，测试
            .builder()         //创建一个builder，然后依次传入参数
            //.url("http://mock.fulingjie.com/mock/api/index.php")   //这是搭建好的用于测试的API
            .url("http://www.51.ca/index.php")   //这是搭建好的用于测试的API
            .loader(this@MainActivity,LoaderStyles.LineSpinFadeLoaderIndicator)        //this@MainActivity
            .params("", "")
            .success(object : ISuccess {                  //成功与否的回调，其它几个也可以回调
                override fun onSuccess(response: String) {
                    //TODO("Not yet implemented")
                    Toast.makeText(baseContext,response,Toast.LENGTH_LONG).show()
                   // Toast.makeText(this,response,Toast.LENGTH_LONG).show()
                   // 不可以,注意这里不是 this, 是baseContext
                    //baseContext-getBaseContext()-getApplicationContext()
                }
            })     //当然也可以加入回调
            .failure(object:IFailure{
                override fun onFailure() {
                    TODO("Not yet implemented")
                }
            })
            .build()           // 创建restClient
            .get()             //restClient 中的具体方法调用


    }
}