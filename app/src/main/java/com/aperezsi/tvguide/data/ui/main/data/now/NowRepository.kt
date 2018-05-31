package com.aperezsi.tvguide.data.ui.main.data

import android.util.Log
import com.aperezsi.tvguide.data.service.ProgramAPI
import com.aperezsi.tvguide.data.ui.main.fragment.now.NowPresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by alberto on 31/05/2018.
 */
class NowRepository (val nowPresenter: NowPresenter) : INowRepository {

    private val programAPI by lazy {
        ProgramAPI.create()
    }

    override fun refreshPrograms() {
        programAPI.getNowProgramming()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    apiResponse ->
                    nowPresenter.updatePrograms(apiResponse.response)
                }, {
                    error ->
                    //TODO implement logger
                    Log.e("refreshPrograms ERROR", error.message)
                })
    }

    override fun filterPrograms() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}