package com.sjcoding.networksdk

import com.sjcoding.networksdk.di.NetworkModule

object NetworkSDK {

    fun initialize(baseUrl: String){
        NetworkModule.initialize(baseUrl)
    }

}