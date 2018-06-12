package com.aperezsi.tvguide.data.service

import android.util.Log
import com.aperezsi.tvguide.data.service.interfaces.ProgramAPI
import com.aperezsi.tvguide.data.ui.main.MainPresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class AuthValidator (presenter: MainPresenter) {

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