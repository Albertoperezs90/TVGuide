package com.aperezsi.tvguide.data.ui.main

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.util.Log
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.ui.base.BaseFragment
import com.aperezsi.tvguide.data.ui.main.data.NowAdapter
import com.aperezsi.tvguide.data.ui.main.data.ProgramRepository
import com.aperezsi.tvguide.data.ui.main.fragment.now.NowFragment
import com.aperezsi.tvguide.data.utils.adapters.FragmentAdapter
import com.aperezsi.tvguide.data.utils.helpers.FragmentNavigation
import java.io.Serializable

class MainPresenter constructor(val mainView: MainContract.View) : MainContract.Presenter, FragmentNavigation.Presenter {

    private val programRepository: ProgramRepository = ProgramRepository()
    var nowPrograms: APIResponse? = null


    override fun setNavigation(fragmentManager: FragmentManager, tabLayout: TabLayout, viewPager: ViewPager) {
        val adapter = FragmentAdapter(mainView.getContext(), fragmentManager)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun loadNowProgramRepository() {
        programRepository.getNowPrograms2()
        //nowPrograms = programRepository.getNowPrograms()
    }

    override fun buildAdapter(layout: Int) {
        loadNowProgramRepository()
        val adapter = NowAdapter(mainView.getContext(), layout, nowPrograms!!)
        mainView.attachAdapter(adapter)
    }



}