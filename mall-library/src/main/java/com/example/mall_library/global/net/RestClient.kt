package com.example.mall_library.global.net

import com.example.mall_library.global.net.callback.*

/**
 * 对外开放的客户端（依赖mall_library，直接使用 ）
 */
class RestClient internal constructor(
    private var url:String?,
    private var request: IRequest?,
    private var success: ISuccess?,
    private var failure: IFailure?,
    private var error: IError?,
    private var complete: IComplete?
){
    companion object
    {
        fun builder():RestClientBuilder
        {
            return RestClientBuilder()
        }
    }

    // 有了具体方法，这里写一个实现类
    private fun request(method: HttpMethod){}

    fun get(){request(HttpMethod.GET)}

    fun post(){request(HttpMethod.POST)}

    fun put(){request(HttpMethod.PUT)}

    fun delete(){request(HttpMethod.DELETE)}

}