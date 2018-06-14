package com.aperezsi.tvguide.data.ui.main.data.schedule

import android.util.Log
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.service.Storage
import com.aperezsi.tvguide.data.service.interfaces.ProgramAPI
import com.aperezsi.tvguide.data.ui.main.fragment.schedule.SchedulePresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ScheduleRepository (val schedulePresenter: SchedulePresenter) : IScheduleRepository {

    private var lastChannel: Int = 0

    val programApi by lazy {
        ProgramAPI.create()
    }

    override fun mapProgramResponseToScheduleProgamming() {
        val idChannelList = Storage(schedulePresenter.scheduleView.getFragmentActivity()).getIdChannels()
        lastChannel = idChannelList.last().toInt()

        idChannelList.forEach { id ->
            programApi.getChannelProgamming(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ apiResponse ->
                        schedulePresenter.addProgramListToScheduleProgramming(apiResponse.response)
                        if (lastChannel == id.toInt()){
                            schedulePresenter.initBuilders()
                        }
                    }, {
                        error ->
                        //TODO implement logger
                        Log.e("mapProgamResponse ERROR", error.message)
                    })
        }
    }
}