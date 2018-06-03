package com.aperezsi.tvguide.data.ui.channel

import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import com.aperezsi.tvguide.data.data.ChannelProgamming
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.channel.data.ChannelRepository
import com.aperezsi.tvguide.data.utils.adapters.FragmentChannelAdapter
import com.aperezsi.tvguide.data.utils.helpers.FragmentNavigation

class ChannelPresenter (val channelView: ChannelContract.View) : ChannelContract.Presenter, FragmentNavigation.Presenter {

    private val channelRepository = ChannelRepository(this)
    private var channelPrograms: ChannelProgamming? = null

    override fun setNavigation(fragmentManager: FragmentManager, tabLayout: TabLayout?, viewPager: ViewPager?) {
        val adapter = FragmentChannelAdapter(channelPrograms!!, channelView.getActivityContext(), fragmentManager)
        viewPager!!.adapter = adapter
        tabLayout!!.setupWithViewPager(viewPager)
    }

    override fun loadData() {
        channelRepository.getChannelProgramming(channelView.extractIdChannel())
    }

    override fun refreshDataList(channelProgamming: ChannelProgamming) {
        channelView.endProgressView()
        if (this.channelPrograms == null){
            this.channelPrograms = channelProgamming
            channelView.setFragmentNavigation()
        }else {
            this.channelPrograms = channelProgamming
        }
    }
}