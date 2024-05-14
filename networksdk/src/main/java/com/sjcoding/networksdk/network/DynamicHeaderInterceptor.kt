package com.sjcoding.networksdk.network

import okhttp3.Interceptor
import okhttp3.Response

class DynamicHeaderInterceptor(private val dynamicHeadersProvider: DynamicHeadersProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        dynamicHeadersProvider.getDynamicHeaders().forEach { (key, value) ->
            requestBuilder.header(key, value)
        }
        return chain.proceed(requestBuilder.build())
    }
}