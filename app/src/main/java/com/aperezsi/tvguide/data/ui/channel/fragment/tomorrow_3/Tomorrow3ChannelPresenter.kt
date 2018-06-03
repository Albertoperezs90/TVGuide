package com.aperezsi.tvguide.data.ui.channel.fragment.tomorrow_3

import com.aperezsi.tvguide.data.ui.channel.data.tomorrow3.Tomorrow3ChannelAdapter

/**
 * Created by alberto on 31/05/2018.
 */
class Tomorrow3ChannelPresenter (val tomorrow3ChannelView: Tomorrow3ChannelContract.View) : Tomorrow3ChannelContract.Presenter {


    override fun buildAdapter(layout: Int): Tomorrow3ChannelAdapter {
        val adapter = Tomorrow3ChannelAdapter(tomorrow3ChannelView.getFragmentContext(), layout, tomorrow3ChannelView.getPrograms())
        return adapter
    }
}