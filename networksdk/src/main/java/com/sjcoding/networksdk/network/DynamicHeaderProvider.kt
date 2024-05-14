package com.sjcoding.networksdk.network

interface DynamicHeadersProvider {
    fun getDynamicHeaders(): Map<String, String>
}