package com.aperezsi.tvguide.data.ui.channel

import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.ui.channel.fragment.ChannelFragment
import com.aperezsi.tvguide.data.utils.helpers.FragmentNavigation
import kotlinx.android.synthetic.main.activity_channel.view.*

class ChannelPresenter (val channelView: ChannelContract.View) : ChannelContract.Presenter, FragmentNavigation.Presenter {

    override fun setNavigation(fragmentManager: FragmentManager, tabLayout: TabLayout?, viewPager: ViewPager?) {
        val fragment = ChannelFragment()
        fragmentManager.beginTransaction().add(R.id.initial_channel_container, fragment).commit()
    }
}