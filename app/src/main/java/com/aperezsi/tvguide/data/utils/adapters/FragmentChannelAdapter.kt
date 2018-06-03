package com.aperezsi.tvguide.data.utils.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.ChannelProgamming
import com.aperezsi.tvguide.data.ui.channel.fragment.today.TodayChannelFragment
import com.aperezsi.tvguide.data.ui.channel.fragment.tomorrow.TomorrowChannelFragment
import com.aperezsi.tvguide.data.ui.channel.fragment.tomorrow_1.Tomorrow1ChannelFragment
import com.aperezsi.tvguide.data.ui.channel.fragment.tomorrow_2.Tomorrow2ChannelFragment
import com.aperezsi.tvguide.data.ui.channel.fragment.tomorrow_3.Tomorrow3ChannelFragment

/**
 * Created by alberto on 07/05/2018.
 */
class FragmentChannelAdapter (val channelPrograms: ChannelProgamming, context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var context: Context = context

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> TodayChannelFragment(channelPrograms.today)
            1 -> TomorrowChannelFragment(channelPrograms.tomorrow)
            2 -> Tomorrow1ChannelFragment(channelPrograms.tomorrow1)
            3 -> Tomorrow2ChannelFragment(channelPrograms.tomorrow2)
            4 -> Tomorrow3ChannelFragment(channelPrograms.tomorrow3)
            else -> null
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> context.resources.getString(R.string.fragment_now_title)
            1 -> context.resources.getString(R.string.fragment_tomorrow_title)
            2 -> context.resources.getString(R.string.fragment_schedule_title)
            else -> null
        }
    }

    override fun getCount(): Int  = 5
}