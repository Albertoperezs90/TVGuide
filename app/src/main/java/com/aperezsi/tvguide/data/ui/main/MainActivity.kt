package com.aperezsi.tvguide.data.ui.main

import android.content.Context
import android.media.tv.TvContract
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.ui.base.BaseActivity
import com.aperezsi.tvguide.data.ui.main.data.NowAdapter
import com.arlib.floatingsearchview.FloatingSearchView
import com.google.android.gms.common.api.Api
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_now.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.doAsync

class MainActivity : BaseActivity(), MainContract.View {

    private val mainPresenter: MainPresenter = MainPresenter(this)

    override fun getContentResource(): Int = R.layout.activity_main
    override fun getContext(): Context = this
    override fun setFragmentNavigation() = mainPresenter.setNavigation(supportFragmentManager, tabs, viewpager)

    override fun onStart() {
        super.onStart()
        attachDrawerLayout()
        mSearchView.setOnQueryChangeListener({ oldQuery: String, newQuery: String ->
            mSearchView.swapSuggestions(mainPresenter.filterSuggestions(oldQuery, newQuery))
        })
    }


    override fun onBackPressed() {
        //Nothing
    }

    override fun setPrograms() {
        mainPresenter.setProgramsList(intent.getSerializableExtra("programs") as APIResponse)
    }

    override fun attachDrawerLayout() {
        mSearchView.attachNavigationDrawerToMenuButton(drawerLayout)
    }

}