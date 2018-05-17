package com.aperezsi.tvguide.data.service

import com.aperezsi.tvguide.data.data.APIResponse
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import rx.Observable

interface ProgramAPI {

    @GET("now")
    fun getNowProgramming() : Observable<APIResponse>

    companion object {
        fun create(): ProgramAPI {

            val gson = GsonBuilder()
                    .setLenient()
                    .create()

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("http://apiguidetv.azurewebsites.net/")
                    .build()
            return retrofit.create(ProgramAPI::class.java)
        }
    }
}