package com.aperezsi.tvguide.data.ui.main

import android.content.Context
import android.media.tv.TvContract
import android.support.v7.widget.Toolbar
import android.util.Log
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.ui.base.BaseActivity
import com.aperezsi.tvguide.data.ui.base.BaseFragment
import com.aperezsi.tvguide.data.utils.adapters.FragmentAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity(), MainContract.View {

    private val mainPresenter: MainPresenter = MainPresenter(this)
    //TODO("instanciar el adapter del recycler view")

    override fun getContentResource(): Int = R.layout.activity_main
    override fun getToolbar(): Toolbar = toolbar
    override fun getContext(): Context = this
    override fun setFragmentNavigation() = mainPresenter.setNavigation(supportFragmentManager, tabs, viewpager)

    override fun setNowListPrograms(nowPrograms: List<TvContract.Programs>) {

    }
}