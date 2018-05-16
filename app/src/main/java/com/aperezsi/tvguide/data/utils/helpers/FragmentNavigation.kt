package com.aperezsi.tvguide.data.utils.helpers

import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import com.aperezsi.tvguide.data.ui.base.BaseFragment

/**
 * Created by alberto on 06/05/2018.
 */
interface FragmentNavigation {

    interface View {

    }

    interface Presenter {
        fun setNavigation (fragmentManager: FragmentManager, tabLayout: TabLayout? = null, viewPager: ViewPager? = null)
    }
}