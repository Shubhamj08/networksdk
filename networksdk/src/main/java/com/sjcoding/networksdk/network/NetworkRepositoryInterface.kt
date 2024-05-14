package com.sjcoding.networksdk.network

import com.sjcoding.networksdk.util.Result

interface NetworkRepositoryInterface {
    suspend fun <T> getData(endpoint: String, headers: Map<String, String>? = null): Result<T>
    suspend fun <T> postData(endpoint: String, body: Any, headers: Map<String, String>? = null): Result<T>
    suspend fun <T> putData(endpoint: String, body: Any, headers: Map<String, String>? = null): Result<T>
    suspend fun <T> deleteData(endpoint: String, headers: Map<String, String>? = null): Result<T>
    suspend fun <T> patchData(endpoint: String, body: Any, headers: Map<String, String>? = null): Result<T>
}