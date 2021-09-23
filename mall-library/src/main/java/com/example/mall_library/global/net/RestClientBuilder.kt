package com.example.mall_library.global.net

import com.example.mall_library.global.net.callback.*
import java.util.*
import kotlin.collections.HashSet

/**
 *
 * 构建RestClient 并且初始化参数及回调(因为参数复杂，所以独立成类)
 */
class RestClientBuilder(
    private var url:String?=null,           //所有的null 都可以不赋值这里
    private var request:IRequest?=null,
    private var success:ISuccess?=null,
    private var failure:IFailure?=null,
    private var error: IError?=null,
    private var complete:IComplete?=null
)
{

    private val mParams=WeakHashMap<String,Any>()

    fun url(url:String):RestClientBuilder                   //类似set()传入参数？
    {
        this.url=url
        return this         //注意这里返回本类的对象
    }

    fun params(key:String,value:Any):RestClientBuilder
    {
        //mParams.put(key,value)
        mParams[key]=value;
        //Map可以用类似数组的方式 这样写，因为键值对。 Set不可以
        return this
    }

    fun params(params:WeakHashMap<String,Any>):RestClientBuilder
    {
        mParams.putAll(params)     //如果想一次的传递完成
        return this
    }

    //----------------------------------
    //下面与上面 fun url()类似
    fun onRequest(iRequest: IRequest):RestClientBuilder    //request?
    {
        this.request=iRequest
        return this
    }

    fun success(iSuccess: ISuccess):RestClientBuilder
    {
        this.success=iSuccess
        return this
    }

    fun failure(iFailure:IFailure):RestClientBuilder
    {
        this.failure=iFailure;    return this
    }

    fun error(iError: IError):RestClientBuilder
    {
        this.error=iError
        return this
    }

    fun complete(iComplete: IComplete):RestClientBuilder
    {
        this.complete=iComplete
        return this
    }

    fun build():RestClient
    {
        return RestClient(
            url,mParams,
            request,success,
            failure,error,complete)
    }

}

