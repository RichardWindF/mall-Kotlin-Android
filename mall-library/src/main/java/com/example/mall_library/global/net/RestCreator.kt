package com.example.mall_library.global.net

import com.example.mall_library.global.GlobalKeys
import com.example.mall_library.global.Mall
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.time.toDuration

/**
 *
 * 1：创建RestService 接口以后，
 *
 * 2：创建Retrofit  的客户端 RestCreator的方法，创建Retrofit 的各个实例
 *
 */
class RestCreator private constructor() {
    private object OkHttpHolder
    { //构建OkHttp

        private const val TIME_OUT = 60  //60秒超时标识
        private val BUILDER: OkHttpClient.Builder = OkHttpClient.Builder()
        internal val OK_HTTP_CLIENT = BUILDER
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)    //参数是long
            .build()
    }

    //retrofit 单例方法
    private object RetrofitHolder {

        //http://demo.com/    注意最后这个/ 是一定要带上的
        //后面的http://demo.com/name...
        //只用敲入name...即可
        //从全局的配置中取出baseUrl
        //internal val INSTANCE=RestCreator()
        private val BASE_URL =
            Mall.getConfiguration<String>(GlobalKeys.API_HOST)
        internal val RETROFIT_CLIENT: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpHolder.OK_HTTP_CLIENT)
            .addConverterFactory(ScalarsConverterFactory.create())   //设计模式
            .build()
    }

    //retrofit  的实例
    private object RestServiceHolder
    {
        internal val REST_SERVICE=RetrofitHolder
            .RETROFIT_CLIENT
            .create(RestService::class.java)            //java 类
    }

    val restService:RestService
    get()= RestServiceHolder.REST_SERVICE


 /*   companion object {
        //    val instance:RestCreator
        //get()=RetrofitHolder.INSTANCE
        // get()=RetrofitHolder.INSTANCE
    }*/



}