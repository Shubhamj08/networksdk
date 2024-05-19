package com.sjcoding.networksdk.network

import com.sjcoding.networksdk.util.Result
import java.lang.reflect.Type

interface NetworkRepository {
    suspend fun <T> getData(
        endpoint: String,
        responseType: Type? = Any::class.java,
        headers: Map<String, String>? = null
    ): Result<T>

    suspend fun <T> postData(
        endpoint: String,
        body: Any,
        responseType: Type? = Any::class.java,
        headers: Map<String, String>? = null
    ): Result<T>

    suspend fun <T> putData(
        endpoint: String,
        body: Any,
        responseType: Type? = Any::class.java,
        headers: Map<String, String>? = null
    ): Result<T>

    suspend fun <T> deleteData(
        endpoint: String,
        responseType: Type? = Any::class.java,
        headers: Map<String, String>? = null
    ): Result<T>

    suspend fun <T> patchData(
        endpoint: String,
        body: Any,
        responseType: Type? = Any::class.java,
        headers: Map<String, String>? = null
    ): Result<T>
}