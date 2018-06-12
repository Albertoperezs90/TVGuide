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
import com.aperezsi.tvguide.data.utils.helpers.TimeHelper

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
            0 -> context.resources.getString(R.string.today)
            1 -> context.resources.getString(R.string.tomorrow)
            2 -> TimeHelper().getDayByEpoch(channelPrograms.tomorrow1!![0].EpochStart!!)
            3 -> TimeHelper().getDayByEpoch(channelPrograms.tomorrow2!![0].EpochStart!!)
            4 -> TimeHelper().getDayByEpoch(channelPrograms.tomorrow3!![0].EpochStart!!)
            else -> null
        }
    }

    override fun getCount(): Int  = 5
}