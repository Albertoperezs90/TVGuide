package com.aperezsi.tvguide.data.service

import android.content.Context
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.APIResponse
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import rx.Observable

interface ProgramAPI {

    @GET("now")
    fun getNowProgramming() : Observable<APIResponse>

    companion object {
        fun create(context: Context): ProgramAPI {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(context.getString(R.string.host))
                    .build()
            return retrofit.create(ProgramAPI::class.java)
        }
    }
}