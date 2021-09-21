package com.example.mallkotlindemo

import android.app.Application
import com.example.mall_library.global.Configurator
import com.example.mall_library.global.Mall

//自己这样写Application要在manifest.xml中添加 name 属性-按Application 的方法
class MallExampleApp:Application()          //继承Application
{
    override fun onCreate()
    {
        super.onCreate()
        //Configurator().      //怎样引用前面 写好的配置代码呢? 所以 配置器那边就应该有个对外公开访问的接口，回到那边

       // Mall().xx        //此时 Mall.XX 出不来，发现 Mall.kt 中类要是静态的 object 类名,否则就只能Mall().XX
       Mall.init(this)
           .withApiHost("http://123.123.com")       //之后使用远程部署的测试数据
           .configure()                       // 这样写，这里如果很多要配置，就能不断的 .xxx, 很舒适流畅


    }

}