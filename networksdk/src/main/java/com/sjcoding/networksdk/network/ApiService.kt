package com.sjcoding.networksdk.network

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Url

internal interface ApiService {

    @GET
    suspend fun <T> getData(@Url url: String): T

    @POST
    suspend fun <T> postData(@Url url: String, @Body body: Any): T

    @PUT
    suspend fun <T> putData(@Url url: String, @Body body: Any): T

    @DELETE
    suspend fun <T> deleteData(@Url url: String): T

    @PATCH
    suspend fun <T> patchData(@Url url: String, @Body body: Any): T

}