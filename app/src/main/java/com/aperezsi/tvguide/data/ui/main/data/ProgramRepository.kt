package com.aperezsi.tvguide.data.ui.main.data

import android.content.Context
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.service.ProgramAPI
import com.aperezsi.tvguide.data.ui.main.MainPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ProgramRepository (context: Context) : IProgramRepository {

    private val programAPI: ProgramAPI = ProgramAPI.create(context)

    override fun getNowPrograms(): List<APIResponse> {
        programAPI.getNowProgramming()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from())
    }

    override fun getTomorrowPrograms(): List<APIResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSchedulePrograms(): List<APIResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}