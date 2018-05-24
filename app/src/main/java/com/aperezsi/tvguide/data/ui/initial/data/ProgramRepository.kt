package com.aperezsi.tvguide.data.ui.main.data

import android.util.Log
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.service.ProgramAPI
import com.aperezsi.tvguide.data.ui.base.BaseRepository
import com.aperezsi.tvguide.data.ui.initial.InitialPresenter
import com.aperezsi.tvguide.data.ui.initial.fragment.InitialFragmentPresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ProgramRepository (val initialFragmentPresenter: InitialFragmentPresenter) : BaseRepository(), IProgramRepository {


    private val programAPI by lazy {
        ProgramAPI.create()
    }

    override fun getPrimetime(epoch: String) {

        programAPI.getPrimetime(epoch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    apiResponse ->
                        initialFragmentPresenter.loadAPIResponseList(apiResponse)
                }, {
                    error ->
                        //TODO implement logger
                        Log.e("getPrimetime ERROR", error.message)
                })
    }

    override fun getNowPrograms() {

        programAPI.getNowProgramming()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    apiResponse ->
                        initialFragmentPresenter.loadAPIResponseList(apiResponse)
                },{
                    error ->
                        //TODO implement logger
                        Log.e("getNowPrograms ERROR", error.message)
                })
    }

    override fun getTomorrowPrograms() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSchedulePrograms() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}