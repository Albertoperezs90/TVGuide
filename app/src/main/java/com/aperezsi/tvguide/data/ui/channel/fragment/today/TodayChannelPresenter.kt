package com.aperezsi.tvguide.data.ui.channel.fragment.today

import com.aperezsi.tvguide.data.ui.channel.data.today.TodayChannelAdapter

/**
 * Created by alberto on 31/05/2018.
 */
class TodayChannelPresenter (val todayChannelView: TodayChannelContract.View) : TodayChannelContract.Presenter {


    override fun buildAdapter(layout: Int): TodayChannelAdapter {
        val adapter = TodayChannelAdapter(todayChannelView.getFragmentContext(), layout, todayChannelView.getPrograms())
        return adapter
    }

}