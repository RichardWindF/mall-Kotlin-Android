package com.example.mall_library.global.net

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import org.intellij.lang.annotations.JdkConstants
import retrofit2.Call
import retrofit2.http.*

import java.util.*

/**
 *
 *   Retrofit 的使用，见它的相关主页上有简短说明--先写一个service--一个接口
 *   https://square.github.io/retrofit/
 *
 *
 * String 作为返回值，是为了通用
 *
 */
interface RestService                            //Restful API 的接口
{
    @GET                                 //自加，自己写好一个模板，下面粘贴即可--模板规范写法
    fun get(
        url:String?,                     //@Url 前面注解省
        params:WeakHashMap<String,Any>?  //@QueryMap
               // WeakHashMap--会自动的移除Map不再使用的<key,value>, 避免内存使用过高
    ):Call<String>       //Call也是retrofit下的接口

    @FormUrlEncoded                      //retrofit 中http 下的注解
    @POST
    fun post(
        url:String?,                     //@Url 前面注解省
        params:WeakHashMap<String,Any>?  //@QueryMap
    ):Call<String>

    @FormUrlEncoded
            // @PUT 不需要
    fun put(
        url: String?,
        params: WeakHashMap<String, Any>?
    ):Call<String>

    @DELETE
    fun delete(
        url:String?,
        params:WeakHashMap<String,Any>?
    ):Call<String>

    @Streaming    //表示不会一次性把文件下载到内存里，而是下载一部分就写一部分
    @GET   //dowload 本质是个get
    fun download(
        @Url url:String?,
        @QueryMap params:WeakHashMap<String,Any>?
    ):Call<ResponseBody>                             //注意返回值与上面的不同

             //@POST 不需要
    fun upload(
        @Url url:String?,
        @Part file: MultipartBody.Part?          //返回的是个串，这里与上面不同
        //params:WeakHashMap<String,Any>?
    ):Call<String>


}
