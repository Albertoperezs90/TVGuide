package com.aperezsi.tvguide.data.ui.main.data.schedule

import android.util.Log
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.data.ScheduleProgramming
import com.aperezsi.tvguide.data.service.ProgramAPI
import com.aperezsi.tvguide.data.ui.main.fragment.schedule.SchedulePresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ScheduleRepository (val schedulePresenter: SchedulePresenter) : IScheduleRepository {

    private var lastChannel: Int = 0

    val programApi by lazy {
        ProgramAPI.create()
    }

    override fun mapProgramResponseToScheduleProgamming(programs: List<ProgramResponse>) {
        val idChannelList = programs.distinctBy { it.IdChannel }
        lastChannel = idChannelList.last().IdChannel!!.toInt()
        idChannelList.forEach { programResponse ->
            Log.d("ENTRANDO BUCLE", programResponse.IdChannel)
            programApi.getChannelProgamming(programResponse.IdChannel!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        schedulePresenter.addProgramListToScheduleProgramming(it.response)
                        if (lastChannel == it.response[0].IdChannel!!.toInt()){
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