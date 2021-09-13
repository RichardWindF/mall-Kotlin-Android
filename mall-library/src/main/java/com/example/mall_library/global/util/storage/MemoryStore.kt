package com.example.mall_library.global.util.storage

import com.example.mall_library.global.GlobalKeys

/**
 * 这种全局的类，一般要是单例模式--要是安全的
 * 这里使用-静态内部类的方法
 *
 */
class MemoryStore private constructor()     //不能new 的
{
    /**  存储配置的容器？
     * 线程安全的单例模式
     * 深入学习可以仿照Java写法
     */
    private object Holder
    {
        internal val INSTANCE=MemoryStore()
        //internal --模块内可见
    }

    companion object
    {
        val instance:MemoryStore                //内部类？
        get()=Holder.INSTANCE                   //??
        
    }
    
    //---------------------------------
    //存储配置信息用什么数据结构呢？---这里用 HashMap
    private val mDataMap= HashMap<String,Any>()
    //这里存储结构就定义好了,接下来定义一些函数

    //-----------------------------------------------------------------------------------
    //fun getData(key: String):Any?{ return mDataMap[key]} //为什么不用这个呢，当返回不确定时候？
    fun <T> getData(key: String):T   //用泛型表示不确定性：-）
    {
        @Suppress("UNCHECKED_CAST")
        return mDataMap[key] as T        //前面小黄灯，点击生成上面注解，不点也没关系，没有提示

       //等价与 return mDataMap.get(key) as T
    }

    fun addData(key:String,value:Any):MemoryStore
    {//返回值设为MemoryStore, 就可以一溜烟的方式的加数据，如
       // MemoryStore.instance.addData().addData().....
        // MemoryStore.instance.addData("",1).addData("",2).addData("",3)....
        mDataMap[key]=value
        //说明1：HashMap 可以像 ArrayList 一样，数组一样赋值
        //2: 上下两句同样功能？
        //mDataMap.put(key,value)
        return this     //

    }

    //---------------------------------------------------------------------
    //定义了 tag 枚举类 GlobalKeys 之后，来测试的

    //fun addData(key:String,value:Any):MemoryStore--修改函数头为：
    //fun addData(key:GlobalKeys,value:Any):MemoryStore     //为了日后的使用，枚举类的通用写法
    fun addData(key:Enum<*>,value:Any):MemoryStore     //为了日后的使用，枚举类的通用写法
    {
        addData(key.name,value)
        //借用了前面已经写的函数，如果不使用，也可以用下面的这句
        //mDataMap.put(key.name,value)
        return this
    }

    fun <T> getData(key: Enum<*>):T   //用泛型表示不确定性：-）
    {

        @Suppress("UNCHECKED_CAST")
        //前面小黄灯，点击生成上面注解，不点也没关系，没有提示
        //return mDataMap.get(key.name) as T        //或者
        return getData(key.name)
    }
    //这样就能够比较统一的管理 各种 tag 了


}