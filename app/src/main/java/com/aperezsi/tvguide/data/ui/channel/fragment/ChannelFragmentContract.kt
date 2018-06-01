package com.aperezsi.tvguide.data.ui.channel.fragment

import android.content.Context
import android.support.v4.app.FragmentActivity
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView
import com.aperezsi.tvguide.data.ui.channel.data.ChannelAdapter

interface ChannelFragmentContract {

    interface View : BaseView<Presenter>{
        fun getFragmentActivity() : FragmentActivity
        fun getFragmentContext() : Context
        fun buildAdapter()
        fun notifyAdapterChange()
        fun attachAdapter(adapter: ChannelAdapter)
    }

    interface Presenter : BasePresenter{
        fun loadData(idChannel: String)
        fun buildAdapter(layout: Int)
        fun refreshDataList(programs: List<ProgramResponse>)
    }
}