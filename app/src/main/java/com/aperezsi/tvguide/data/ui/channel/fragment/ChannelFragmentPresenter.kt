package com.aperezsi.tvguide.data.ui.channel.fragment

import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.channel.data.ChannelAdapter
import com.aperezsi.tvguide.data.ui.channel.data.ChannelRepository

class ChannelFragmentPresenter (val channelFragment: ChannelFragmentContract.View) : ChannelFragmentContract.Presenter {

    private val channelRepository = ChannelRepository(this)
    private var programs: List<ProgramResponse>? = null

    override fun loadData(idChannel: String) {
        channelRepository.getChannelProgamming(idChannel)
    }

    override fun buildAdapter(layout: Int) {
        val adapter = ChannelAdapter(channelFragment.getFragmentContext(), layout, programs!!)
        channelFragment.attachAdapter(adapter)
        channelFragment.notifyAdapterChange()
    }

    override fun refreshDataList(programs: List<ProgramResponse>) {
        if (this.programs == null){
            this.programs = programs
            channelFragment.buildAdapter()
        }else {
            this.programs = programs
            channelFragment.notifyAdapterChange()
        }
    }

}