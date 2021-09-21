package com.example.mall_library.global

import com.example.mall_library.global.util.storage.MemoryStore
import java.lang.RuntimeException
import java.util.logging.Handler

/**
 *  管理全局的 配置的控制类
 *  这里也是 全局的单例模式 ，但采用了另一种写法
 */
class Configurator private constructor()                 //即不能new 的
{
    //Configurator 是配置管理器， MemoryStore 是存储配置的容器
    private object Holder
    {
        internal val INSTANCE=Configurator()
    }

    companion object
    {
       internal val instance:Configurator                            //内部类？
        get()=Holder.INSTANCE

        //------------------------------------------------------------
        //不妨加些东西：
        // 这里获得全局的存储容器
        private val mStore:MemoryStore= MemoryStore.instance
        //Handler需要反复使用，这里不妨提前创建
        //private val mHandler=Handler()    //这个红线无法通过,抽象类?
        //private val mHandler=MyHandler()   //现在好像只能这样，一个类继承抽象类Handler，实现方法，。。然后这里使用

        private val mHandler=android.os.Handler()
    }

    //-------------------------------------------------------------
    //现在有了 MemoryStore 和 Configurator 两个类，如何做全局配置呢？-比如下面：

    init {
        //加一个标签，判断是否配置完成
        mStore.addData("IS_READY",true)
             //这里有个问题，这种标签，久了自己都不一定记得住，怎么办呢？
             // 法1：建议使用-枚举类-来做， 右击-new-Kotlin File/Class-enum class
             //法2：当然也可以用个类，里面存储静态字符串或int变量（可以这样做，但不建议）

        //----------------------------------------------------
        //此时去创建了枚举类 GlobalKeys.kt
        //加一个标签，判断是否配置完成--现在还没配置
        mStore.addData(GlobalKeys.IS_CONFIGURE_READY,false)
        mStore.addData(GlobalKeys.HANDLER, mHandler)

    }

    //既然已经init了，不妨设置些什么
    /**
     * 访问服务器端API的设置
     */
    fun withApiHost(host:String):Configurator  //也是为了顺溜着设置，所以返回值为Configurator
    {
        mStore.addData(GlobalKeys.API_HOST,host)
        return this
    }

    /**
     * 结束配置，所以不返回任何东西
     *
     */
    fun configure()
    {
        mStore.addData(GlobalKeys.IS_CONFIGURE_READY,true)
        //下面可以做一些回收的动作
    }

    private fun checkConfiguration()
    {
        val isReady:Boolean
                = mStore.getData<Boolean>(GlobalKeys.IS_CONFIGURE_READY)
        if(!isReady)
        {
            throw RuntimeException("Config is not ready!")
        }
    }

    // <T>为泛型参数，  代表不确定因素，也可以说是任意的，动态的
    fun <T>getConfiguration(key:String):T    //因为不知道是什么类型，所以加上 泛型<T>
    {
        checkConfiguration()
        return mStore.getData(key)

    }

    fun <T>getConfiguration(key:Enum<*>):T    //因为不知道是什么类型，所以加上 泛型<T>--重载上面的
    {
        //checkConfiguration()
        //return mStore.getData(key)
        return getConfiguration(key.name)

    }

}
