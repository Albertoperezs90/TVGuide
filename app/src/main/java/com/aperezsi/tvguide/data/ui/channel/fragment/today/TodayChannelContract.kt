package com.aperezsi.tvguide.data.ui.channel.fragment.today

import android.content.Context
import android.support.v4.app.FragmentActivity
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView
import com.aperezsi.tvguide.data.ui.channel.data.today.TodayChannelAdapter

/**
 * Created by alberto on 31/05/2018.
 */
interface TodayChannelContract {

    interface View : BaseView<Presenter>{
        fun getFragmentActivity() : FragmentActivity
        fun getFragmentContext() : Context
        fun refreshAdapter()
        fun getPrograms() : List<ProgramResponse>
    }

    interface Presenter : BasePresenter {
        fun buildAdapter(layout: Int) : TodayChannelAdapter
    }
}