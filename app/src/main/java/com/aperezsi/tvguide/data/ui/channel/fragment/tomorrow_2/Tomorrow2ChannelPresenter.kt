package com.aperezsi.tvguide.data.ui.channel.fragment.tomorrow_2

import com.aperezsi.tvguide.data.ui.channel.data.tomorrow2.Tomorrow2ChannelAdapter

/**
 * Created by alberto on 31/05/2018.
 */
class Tomorrow2ChannelPresenter (val tomorrow2ChannelView: Tomorrow2ChannelContract.View) : Tomorrow2ChannelContract.Presenter {

    override fun buildAdapter(layout: Int): Tomorrow2ChannelAdapter {
        val adapter = Tomorrow2ChannelAdapter(tomorrow2ChannelView.getFragmentContext(), layout, tomorrow2ChannelView.getPrograms())
        return adapter
    }
}