package com.aperezsi.tvguide.data.ui.main

import android.app.Activity
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.ui.main.data.NowAdapter
import com.aperezsi.tvguide.data.utils.adapters.FragmentAdapter
import com.aperezsi.tvguide.data.utils.helpers.FragmentNavigation

class MainPresenter constructor(val mainView: MainContract.View) : MainContract.Presenter, FragmentNavigation.Presenter {

    var nowPrograms: APIResponse? = null


    override fun setNavigation(fragmentManager: FragmentManager, tabLayout: TabLayout?, viewPager: ViewPager?) {
        val adapter = FragmentAdapter(mainView.getContext(), fragmentManager)
        viewPager!!.adapter = adapter
        tabLayout!!.setupWithViewPager(viewPager)
    }
}