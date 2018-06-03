package com.aperezsi.tvguide.data.ui.channel.fragment.tomorrow

import com.aperezsi.tvguide.data.ui.channel.data.tomorrow.TomorrowChannelAdapter

/**
 * Created by alberto on 31/05/2018.
 */
class TomorrowChannelPresenter (val tomorrowChannelView: TomorrowChannelContract.View) : TomorrowChannelContract.Presenter {

    override fun buildAdapter(layout: Int): TomorrowChannelAdapter {
        val adapter = TomorrowChannelAdapter(tomorrowChannelView.getFragmentContext(), layout, tomorrowChannelView.getPrograms())
        return adapter
    }
}