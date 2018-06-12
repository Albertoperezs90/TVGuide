package com.aperezsi.tvguide.data.ui.main.fragment.schedule

import android.util.Log
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.data.ScheduleProgramming
import com.aperezsi.tvguide.data.service.Storage
import com.aperezsi.tvguide.data.ui.main.data.schedule.ScheduleAdapter
import com.aperezsi.tvguide.data.ui.main.data.schedule.ScheduleRepository

class SchedulePresenter (val scheduleView: ScheduleContract.View): ScheduleContract.Presenter {

    private val repository: ScheduleRepository = ScheduleRepository(this)
    private val scheduleProgramming = mutableListOf<ScheduleProgramming>()

    override fun initBuilders() {
        scheduleView.buildAdapter()
    }

    override fun buildAdapter(layout: Int): ScheduleAdapter {
        val adapter = ScheduleAdapter(scheduleView.getFragmentContext(), layout, scheduleProgramming)
        return adapter
    }

    override fun loadData() {
        repository.mapProgramResponseToScheduleProgamming()
    }

    override fun addProgramListToScheduleProgramming(programs: List<ProgramResponse>) {
        scheduleProgramming.add(ScheduleProgramming(programs.toMutableList()))
    }

}