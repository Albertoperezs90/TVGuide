package com.aperezsi.tvguide.data.utils.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.channel.fragment.today.TodayChannelFragment
import com.aperezsi.tvguide.data.ui.main.fragment.now.NowFragment
import com.aperezsi.tvguide.data.ui.main.fragment.schedule.ScheduleFragment
import com.aperezsi.tvguide.data.ui.main.fragment.tomorrow.TomorrowFragment

/**
 * Created by alberto on 07/05/2018.
 */
class FragmentChannelAdapter (val channelPrograms: List<ProgramResponse>, context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var context: Context = context

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> TodayChannelFragment()
            1 -> TomorrowFragment()
            2 -> ScheduleFragment()
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

    override fun getCount(): Int  = 3
}