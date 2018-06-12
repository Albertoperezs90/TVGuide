package com.aperezsi.tvguide.data.utils.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.main.fragment.now.NowFragment
import com.aperezsi.tvguide.data.ui.main.fragment.schedule.ScheduleFragment
import com.aperezsi.tvguide.data.ui.main.fragment.tomorrow.TomorrowFragment
import android.util.SparseArray



/**
 * Created by alberto on 07/05/2018.
 */
class FragmentAdapter (val nowPrograms: List<ProgramResponse> ,context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var context: Context = context
    var registeredFragments = SparseArray<Fragment>()

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> NowFragment(nowPrograms)
            1 -> TomorrowFragment()
            2 -> ScheduleFragment(nowPrograms)
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

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        registeredFragments.put(position, fragment)
        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        registeredFragments.remove(position)
        super.destroyItem(container, position, `object`)
    }



    override fun getCount(): Int  = 3
}