package com.aperezsi.tvguide.data.ui.main.fragment.schedule

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.base.BaseFragment
import com.aperezsi.tvguide.data.ui.main.data.schedule.ScheduleAdapter
import kotlinx.android.synthetic.main.fragment_schedule.*

@SuppressLint("ValidFragment")
/**
 * Created by alberto on 06/05/2018.
 */
class ScheduleFragment(val nowPrograms: List<ProgramResponse>) : BaseFragment(), ScheduleContract.View {


    private val schedulePresenter: SchedulePresenter = SchedulePresenter(this)
    private lateinit var adapter: ScheduleAdapter

    override fun getLayout(): Int = R.layout.fragment_schedule
    override fun getFragmentContext(): Context = context!!
    override fun getFragmentActivity(): FragmentActivity = activity!!

    override fun onStart() {
        schedulePresenter.loadData()
        super.onStart()
    }

    override fun buildAdapter() {
        this.adapter = schedulePresenter.buildAdapter(R.layout.fragment_schedule_row)
        rvSchedule.layoutManager = LinearLayoutManager(context)
        rvSchedule.adapter = this.adapter
    }

    override fun refreshAdapter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshAdapter(programs: List<ProgramResponse>) {
        this.adapter.notifyDataSetChanged()
    }

    override fun getPrograms(): List<ProgramResponse> {
        return nowPrograms
    }
}