package com.example.googlesearch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import java.util.concurrent.Future


object DataLoader {

    const val AUTOCOMPLETE = "autocomplete"
    private val retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl("https://maps.googleapis.com/maps/api/")
            .build()
    }

    private val service = retrofit.create(GitHubService::class.java)

    fun getRequest(path: String, parameters: MutableMap<String,String>, callback: FutureCallback) {
        val call = service.getRequest(path, parameters)
        call?.enqueue(object: Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {

            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                callback.onSuccess(response.body().toString())
            }

        })
    }

    interface GitHubService {
        @GET("place/{path}/json")
        fun getRequest(@Path("path") path: String?, @QueryMap paramteres: MutableMap<String, String>): Call<String>?
    }
}