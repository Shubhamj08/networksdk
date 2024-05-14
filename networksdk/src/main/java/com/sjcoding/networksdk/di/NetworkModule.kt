package com.sjcoding.networksdk.di

import com.sjcoding.networksdk.network.NetworkRepositoryImpl
import com.sjcoding.networksdk.network.NetworkRepositoryInterface
import java.lang.IllegalStateException

object NetworkModule {

    private var baseUrl: String? = null

    fun initialize(baseUrl: String) {
        this.baseUrl = baseUrl
    }

    val networkRepository: NetworkRepositoryInterface
        get() = baseUrl?.let { NetworkRepositoryImpl(it) } ?: throw IllegalStateException("Base URL not initialized")

}