package com.sjcoding.networksdk.network

import com.google.gson.Gson
import com.sjcoding.networksdk.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.reflect.Type

class NetworkRepositoryImpl(private val baseUrl: String) : NetworkRepository {

    private val gson = Gson()
    private fun getApiService(headers: Map<String, String>?): ApiService {
        return NetworkClient.getApiService(baseUrl, headers)
    }

    override suspend fun <T> getData(
        endpoint: String,
        responseType: Type?,
        headers: Map<String, String>?
    ): Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                val apiService = getApiService(headers)
                val response = apiService.getData<T>(endpoint)
                val data = gson.fromJson<T>(gson.toJson(response), responseType)
                Result.Success(data)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    override suspend fun <T> postData(
        endpoint: String,
        body: Any,
        responseType: Type?,
        headers: Map<String, String>?
    ): Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                val apiService = getApiService(headers)
                val response = apiService.postData<T>(endpoint, body)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    override suspend fun <T> putData(
        endpoint: String,
        body: Any,
        responseType: Type?,
        headers: Map<String, String>?
    ): Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                val apiService = getApiService(headers)
                val response = apiService.putData<T>(endpoint, body)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    override suspend fun <T> deleteData(
        endpoint: String,
        responseType: Type?,
        headers: Map<String, String>?
    ): Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                val apiService = getApiService(headers)
                val response = apiService.deleteData<T>(endpoint)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    override suspend fun <T> patchData(
        endpoint: String,
        body: Any,
        responseType: Type?,
        headers: Map<String, String>?
    ): Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                val apiService = getApiService(headers)
                val response = apiService.patchData<T>(endpoint, body)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

}