package com.sjcoding.networksdk

data class Comment(
    val postId: String,
    val index: Int,
    val name: String,
    val email: String,
    val body: String
)