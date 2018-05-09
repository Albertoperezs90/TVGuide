package com.aperezsi.tvguide.data.ui.main.data

import android.content.Context
import android.util.Log
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.service.ProgramAPI
import com.aperezsi.tvguide.data.ui.base.BaseRepository
import com.aperezsi.tvguide.data.ui.main.MainPresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ProgramRepository : BaseRepository() ,IProgramRepository {

    private val programAPI by lazy {
        ProgramAPI.create()
    }


    override fun getNowPrograms(): APIResponse? {

        var programs: APIResponse? = null

        programAPI.getNowProgramming()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe({
                    body ->
                        Log.d("Repository", body.string())
                },{
                    error ->
                        Log.e("getNowPrograms ERROR", error.message)
                })

        return programs
    }

    fun getNowPrograms2() {
        programAPI.getNowProgramming()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    body ->
                    Log.d("Repository", body.string())
                },{
                    error ->
                    Log.e("getNowPrograms ERROR", error.message)
                })
    }

    override fun getTomorrowPrograms(): APIResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSchedulePrograms(): APIResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}