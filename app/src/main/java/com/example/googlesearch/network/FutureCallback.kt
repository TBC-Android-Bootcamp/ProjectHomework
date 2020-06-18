package com.example.googlesearch.network

interface FutureCallback {
    fun onSuccess(result: String){}
    fun onFailure(error: String){}
}