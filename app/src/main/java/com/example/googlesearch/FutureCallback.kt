package com.example.googlesearch

interface FutureCallback {
    fun onSuccess(result: String){}
    fun onFailure(){}
}