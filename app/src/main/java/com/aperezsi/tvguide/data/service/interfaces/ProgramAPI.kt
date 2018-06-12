package com.aperezsi.tvguide.data.service.interfaces

import com.aperezsi.tvguide.data.data.APIResponse
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Observable

interface ProgramAPI {

    @GET("now")
    fun getNowProgramming() : Observable<APIResponse>

    @GET("primetime")
    fun getPrimetime(@Query("epoch") epoch: String) : Observable<APIResponse>

    @GET("channel")
    fun getChannelProgamming(@Query("id") idChannel: String) : Observable<APIResponse>

    @GET("auth")
    fun getToken() : Observable<ResponseBody>

    companion object {
        fun create(): ProgramAPI {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("http://apiguidetv.azurewebsites.net/")
                    .build()
            return retrofit.create(ProgramAPI::class.java)
        }
    }
}