package com.aperezsi.tvguide.data.ui.main

import android.app.Activity
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.util.Log
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.main.data.NowAdapter
import com.aperezsi.tvguide.data.utils.adapters.FragmentAdapter
import com.aperezsi.tvguide.data.utils.helpers.FragmentNavigation

class MainPresenter constructor(val mainView: MainContract.View) : MainContract.Presenter, FragmentNavigation.Presenter {

    var nowPrograms: List<ProgramResponse>? = null

    override fun setProgramsList(nowPrograms: APIResponse) {
        this.nowPrograms = nowPrograms.response
    }

    override fun setNavigation(fragmentManager: FragmentManager, tabLayout: TabLayout?, viewPager: ViewPager?) {
        mainView.setPrograms()
        val adapter = FragmentAdapter(nowPrograms!!, mainView.getContext(), fragmentManager)
        viewPager!!.adapter = adapter
        tabLayout!!.setupWithViewPager(viewPager)
    }

    override fun filterSuggestions(oldQuery: String, newQuery: String) : MutableList<ProgramResponse> {
        return nowPrograms!!.filter { it.Title!!.startsWith(newQuery) }.toMutableList()
    }
}