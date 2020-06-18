package com.example.googlesearch.network

import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

object DataLoader {
    const val PATH = "90aa5377-649b-4e39-ab7c-fa6a21a6882d"

    private const val HTTP_200_OK = 200
    private const val HTTP_201_CREATED = 201
    private const val HTTP_204_NO_CONTENT = 204
    private const val HTTP_400_BAD_REQUEST = 400
    private const val HTTP_401_UNAUTHORIZED = 401
    private const val HTTP_404_NOT_FOUND = 404
    private const val HTTP_500_INTERNAL_SERVER_ERROR = 500
    private const val HTTP_503_SERVICE_UNAVAILABLE = 503


    private var retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl("https://run.mocky.io/v3/")
        .build()

    private var service = retrofit.create(
        ApiService::class.java)

    fun getRequest(path: String, parameters: Map<String, String>, callback: FutureCallback) {
        val call = service.getRequest(path, parameters)
        call.enqueue(
            onCallBack(
                callback
            )
        )
    }

    private fun onCallBack(callback: FutureCallback) = object : Callback<String> {
        override fun onFailure(call: Call<String>, t: Throwable) {
            Log.d("onFailure", "${t.message}")
            callback.onFailure(t.message.toString())
        }

        override fun onResponse(call: Call<String>, response: Response<String>) {
            Log.d("response", "${response.body()}")

            val statusCode = response.code()

            if (statusCode == HTTP_200_OK || statusCode == HTTP_201_CREATED) {
                callback.onSuccess(response.body().toString())
            } else if (statusCode == HTTP_204_NO_CONTENT) {
                callback.onFailure("No Content Received")
            } else if (statusCode == HTTP_400_BAD_REQUEST || statusCode == HTTP_401_UNAUTHORIZED || statusCode == HTTP_404_NOT_FOUND || statusCode == HTTP_500_INTERNAL_SERVER_ERROR || statusCode == HTTP_503_SERVICE_UNAVAILABLE) {
                try {
                    val json = JSONObject(response.errorBody()!!.string())
                    if (json.has("error")) {
                        callback.onFailure(json.getString("error"))
                    } else if (json.has("message")) {
                        callback.onFailure(json.getString("message"))
                    }
                } catch (e: JSONException) {
                    Log.d("exception", e.toString())
                }
            }
        }
    }

    interface ApiService {
        @GET("{path}")
        fun getRequest(
            @Path("path") path: String,
            @QueryMap parameters: Map<String, String>
        ): Call<String>
    }
}