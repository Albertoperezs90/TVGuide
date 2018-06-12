package com.aperezsi.tvguide.data.ui.main.fragment.schedule

import android.content.Context
import android.support.v4.app.FragmentActivity
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView
import com.aperezsi.tvguide.data.ui.main.data.schedule.ScheduleAdapter

interface ScheduleContract {

    interface View : BaseView<Presenter> {
        fun getFragmentContext() : Context
        fun getFragmentActivity() : FragmentActivity
        fun buildAdapter()
        fun refreshAdapter()
        fun getPrograms() : List<ProgramResponse>
    }

    interface Presenter : BasePresenter {
        fun initBuilders()
        fun loadData()
        fun buildAdapter(layout: Int) : ScheduleAdapter
        fun addProgramListToScheduleProgramming(programs: List<ProgramResponse>)
    }
}