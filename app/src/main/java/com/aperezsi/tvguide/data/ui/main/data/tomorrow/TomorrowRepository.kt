package com.aperezsi.tvguide.data.ui.main.data.tomorrow

import android.util.Log
import com.aperezsi.tvguide.data.service.interfaces.ProgramAPI
import com.aperezsi.tvguide.data.ui.main.fragment.tomorrow.TomorrowPresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by alberto on 31/05/2018.
 */
class TomorrowRepository (val tomorrowPresenter: TomorrowPresenter) : ITomorrowRepository {


    private val programAPI by lazy {
        ProgramAPI.create()
    }

    override fun getTomorrowPrograms(epoch: String) {
        programAPI.getPrimetime(epoch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    apiResponse ->
                    tomorrowPresenter.updatePrograms(apiResponse.response)
                }, {
                    error ->
                    //TODO implement logger
                    Log.e("refreshPrograms ERROR", error.message)
                })
    }

    override fun refreshPrograms(epoch: String) {
        programAPI.getPrimetime(epoch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    apiResponse ->
                    tomorrowPresenter.updatePrograms(apiResponse.response)
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