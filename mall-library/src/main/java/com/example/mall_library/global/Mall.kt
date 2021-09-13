package com.example.mall_library.global

import android.content.Context
import com.example.mall_library.global.util.storage.MemoryStore

//class Mall      //这里要是静态的类?-否则那边Mall.XX 点不出来,就只能 Mall().xx 了。换成下面这个就可以了
object Mall      //这里要是静态的类-否则那边Mall.XX 点不出来,就只能 Mall().xx 了, 静态的类--object 类名。
{
    //因为Configurator 是配置系统的类，MemoryStore 是存储配置的容器，那么外界操作他们的时候，
    //就不应该直接的去操作，而应该写一个类去总体控制这个操作，所以这个类 Mall 产生了--外面访问的统一接口

    //val configurator:Configurator= Configurator.instance;  // 产生一个 Configurator 的单例类的对象
    val configurator:Configurator
    get() = Configurator.instance;  // 产生一个 Configurator 的单例类     //上面这两者一致吗？


    fun init(context:Context):Configurator    //注意这里和初始化块 init{} 意义不一样，这里是个函数
    {
        MemoryStore.instance
            .addData(
                GlobalKeys.APPLICATION_CONTEXT,
                context.applicationContext)       //就把全局的context 存在了MemoryStore 里面

        return Configurator.instance
    }

    //-------------------------------------------------------------------------------
    //补全这些函数方法？
    fun<T>getConfiguration(key:String):T
    {
        return configurator.getConfiguration(key)
    }

    //fun<T>getConfiguration(key:Enum<*>):T           //Enum, 不是enum      //重载
    fun<T>getConfiguration(key:Enum<GlobalKeys>):T           //也可以是 GlobalKeys
    {

        return getConfiguration(key.name)         //借用了上面的函数
    }

    //这里可以写一些特有的获取值的方法等等 getContext() 什么的
    //------------------------------------------------------------------------------------

}