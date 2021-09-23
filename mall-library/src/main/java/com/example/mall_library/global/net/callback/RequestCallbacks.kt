package com.example.mall_library.global.net.callback

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//该类实现retrofit2 中的接口Callback
class RequestCallbacks (
    private var request: IRequest?,
    private val success: ISuccess?,       //var->val
    private val failure: IFailure?,       //var->val
    private val error: IError?,           //var->val
    private var complete: IComplete?
    ):Callback<String>                   //实现接口retrofit2.Callback，并实现其方法
{
    override fun onResponse(call: Call<String>, response: Response<String>)
    {
        if (response.isSuccessful) {
            if (call.isExecuted) {
                if (success!=null) {
                    if (response.body()!=null) {
                        success.onSuccess(response.body()!!)
                        //此时出红线，要求该上面 定义不能是变量，改成常量
                    }
                }
            }
        }else//不成功
        {
            if (error!=null) {
                error.onError(response.code(),response.message())
            } // 等价 error?.onError(response.code(),response.message())
        }

    }


    override fun onFailure(call: Call<String>, t: Throwable)
    {
        if (failure!=null) {
            failure.onFailure()
        } //failure?.onFailure()

        request?.onRequestEnd()
    }
}

//网路请求搭建完成