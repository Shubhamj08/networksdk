package com.sjcoding.networksdk.network

import com.sjcoding.networksdk.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class NetworkRepositoryImpl(private val baseUrl: String) : NetworkRepositoryInterface {

    private fun getApiService(headers: Map<String, String>?): ApiService {
        return NetworkClient.getApiService(baseUrl, headers)
    }

    override suspend fun <T> getData(endpoint: String, headers: Map<String, String>?): Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                val apiService = getApiService(headers)
                val response = apiService.getData<T>(endpoint)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    override suspend fun <T> postData(
        endpoint: String,
        body: Any,
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

    override suspend fun <T> deleteData(endpoint: String, headers: Map<String, String>?): Result<T> {
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