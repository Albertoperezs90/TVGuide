package com.aperezsi.tvguide.data.utils.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.aperezsi.tvguide.data.ui.main.fragment.now.NowFragment

/**
 * Created by alberto on 07/05/2018.
 */
class FragmentAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> NowFragment()
            else -> null
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return super.getPageTitle(position)
    }

    override fun getCount(): Int  = 1
}