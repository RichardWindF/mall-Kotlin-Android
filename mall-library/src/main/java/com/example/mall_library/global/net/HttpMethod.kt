package com.example.mall_library.global.net

enum class HttpMethod
{
    GET,
    POST,
    PUT,
    DELETE,
    UPLOAD     //上传
}

//retrofit 的官网上有这个用法，要先建 service 接口什么的
//同样在 包net 下去建