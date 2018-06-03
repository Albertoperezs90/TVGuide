package com.aperezsi.tvguide.data.ui.channel.fragment.tomorrow_1

import com.aperezsi.tvguide.data.ui.channel.data.tomorrow1.Tomorrow1ChannelAdapter

/**
 * Created by alberto on 31/05/2018.
 */
class Tomorrow1ChannelPresenter (val tomorrow1ChannelView: Tomorrow1ChannelContract.View) : Tomorrow1ChannelContract.Presenter {


    override fun buildAdapter(layout: Int): Tomorrow1ChannelAdapter {
        val adapter = Tomorrow1ChannelAdapter(tomorrow1ChannelView.getFragmentContext(), layout, tomorrow1ChannelView.getPrograms())
        return adapter
    }
}