package com.aperezsi.tvguide.data.ui.main

import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import com.aperezsi.tvguide.data.ui.base.BaseFragment
import com.aperezsi.tvguide.data.ui.main.fragment.now.NowFragment
import com.aperezsi.tvguide.data.utils.adapters.FragmentAdapter
import com.aperezsi.tvguide.data.utils.helpers.FragmentNavigation

class MainPresenter constructor(mainView: MainContract.View) : MainContract.Presenter, FragmentNavigation.Presenter {

    private var mainView: MainContract.View

    init {
        this.mainView = mainView
    }

    override fun setNavigation(fragmentManager: FragmentManager, tabLayout: TabLayout, viewPager: ViewPager) {
        val adapter = FragmentAdapter(mainView.getContext(), fragmentManager)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}