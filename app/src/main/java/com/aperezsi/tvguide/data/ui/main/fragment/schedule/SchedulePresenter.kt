package com.aperezsi.tvguide.data.ui.main.fragment.schedule

import com.aperezsi.tvguide.data.data.ScheduleProgramming
import com.aperezsi.tvguide.data.ui.main.data.schedule.ScheduleAdapter

class SchedulePresenter (val scheduleView: ScheduleContract.View): ScheduleContract.Presenter {

    override fun buildAdapter(layout: Int): ScheduleAdapter {
        val programs = scheduleView.getPrograms()
        val scheduleProgramList = mutableListOf<ScheduleProgramming>()
        //TODO filtrar programs por cadena
        //TODO por cada list de cadenas filtradas, introducir al scheduleProgramList.AddAll(lista programs)
        val adapter = ScheduleAdapter(scheduleView.getFragmentContext(), layout, scheduleProgramList)
        return adapter
    }

}