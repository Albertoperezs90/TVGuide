package com.aperezsi.tvguide.data.ui.initial

import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.ui.initial.fragment.InitialFragment
import com.aperezsi.tvguide.data.utils.helpers.FragmentNavigation

/**
 * Created by alberto on 17/05/2018.
 */
class InitialPresenter : InitialContract.Presenter, FragmentNavigation.Presenter {


    override fun setNavigation(fragmentManager: FragmentManager, tabLayout: TabLayout?, viewPager: ViewPager?) {
        val fragment = InitialFragment()
        fragmentManager.beginTransaction().add(R.id.initial_container, fragment).commit()
    }
}