package com.aperezsi.tvguide.data.service

import android.util.Log
import com.aperezsi.tvguide.data.service.interfaces.ProgramAPI
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

object AuthValidator {

    private val programApi by lazy {
        ProgramAPI.create()
    }

    fun getToken(){
        programApi.getToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("token", it.string())
                }, { error ->
                    //TODO implement logger
                    Log.e("getToken ERROR", error.message)
                })
    }
}