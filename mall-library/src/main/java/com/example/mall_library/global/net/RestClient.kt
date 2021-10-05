package com.example.mall_library.global.net

import android.content.Context
import com.example.mall_library.global.net.callback.*
import com.example.mall_library.global.ui.loader.LoaderStyles
import com.example.mall_library.global.ui.loader.MallLoader
import retrofit2.Call
import retrofit2.Callback
import java.util.*

/**
 * 对外开放的客户端（依赖mall_library，直接使用 ）
 */
class RestClient internal constructor(
    private var url:String?,
    private val params:WeakHashMap<String,Any>?,      //忘记添加了
    private var request: IRequest?,
    private var success: ISuccess?,
    private var failure: IFailure?,
    private var error: IError?,
    private var complete: IComplete?,

    private var context: Context?,               //添加这2个
    private val loaderStyle: LoaderStyles?

){
    companion object
    {
        fun builder():RestClientBuilder
        {
            return RestClientBuilder()
        }
    }

    // 有了具体方法，这里写一个实现类
    private fun request(method: HttpMethod)
    {
        val service=RestCreator.restService
        val call: Call<String>?       //这些是retrofit or okhttp 的用法，用时去查文档
        request?.onRequestStart()

        if (loaderStyle!=null)
        {                    //添加
            MallLoader.showLoading(context, loaderStyle)
            //Kotlin中不可更改得值，尽量用val ,比如这里  loaderStyle
        }
        call=when(method)           //when 有红线提示，保持已有。。。
        {
            HttpMethod.GET -> service.get(url,params)
            HttpMethod.POST -> service.post(url,params)
            HttpMethod.PUT -> service.put(url,params)
            HttpMethod.DELETE -> service.delete(url,params)
            //下面两先不实现
            HttpMethod.UPLOAD -> TODO()
            HttpMethod.DOWNLOAD -> TODO()
        }

        call.enqueue(requestCallback)
    //此处参数是个callback 的类，要去net包下面的包callback中建一个新类callback
    }

    private val requestCallback:Callback<String>
    get()=RequestCallbacks(request,success,failure,error,complete,loaderStyle)



    fun get(){request(HttpMethod.GET)}

    fun post(){request(HttpMethod.POST)}

    fun put(){request(HttpMethod.PUT)}

    fun delete(){request(HttpMethod.DELETE)}

}