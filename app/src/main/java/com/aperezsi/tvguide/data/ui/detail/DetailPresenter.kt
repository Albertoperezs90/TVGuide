package com.aperezsi.tvguide.data.ui.detail

import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import com.aperezsi.tvguide.data.utils.helpers.FragmentNavigation

class DetailPresenter (val detailView: DetailContract.View) : DetailContract.Presenter, FragmentNavigation.Presenter {

    override fun setNavigation(fragmentManager: FragmentManager, tabLayout: TabLayout?, viewPager: ViewPager?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}