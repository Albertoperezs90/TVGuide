package com.aperezsi.tvguide.data.ui.main.fragment.schedule

import android.os.Bundle
import android.util.Log
import android.view.View
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.ui.base.BaseFragment

/**
 * Created by alberto on 06/05/2018.
 */
class ScheduleFragment : BaseFragment(), ScheduleContract.View {

    private lateinit var schedulePresenter: SchedulePresenter

    override fun getLayout(): Int = R.layout.fragment_schedule

    override fun onStart() {
        super.onStart()
        schedulePresenter = SchedulePresenter()
    }
}