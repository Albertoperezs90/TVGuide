package com.aperezsi.tvguide.data.ui.main

import android.content.Context
import android.media.tv.TvContract
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.ui.base.BaseActivity
import com.aperezsi.tvguide.data.ui.main.data.NowAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_now.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.doAsync

class MainActivity : BaseActivity(), MainContract.View {

    private val mainPresenter: MainPresenter = MainPresenter(this)
    private var apiResponse: APIResponse? = null
    private lateinit var adapter: NowAdapter
    //TODO("instanciar el adapter del recycler view")

    override fun getContentResource(): Int = R.layout.activity_main
    override fun getToolbar(): Toolbar = toolbar
    override fun getContext(): Context = this
    override fun setFragmentNavigation() = mainPresenter.setNavigation(supportFragmentManager, tabs, viewpager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doAsync {
            mainPresenter.buildAdapter(R.layout.fragment_now_row)
        }
    }


    override fun setNowListPrograms(nowPrograms: APIResponse) {
        apiResponse = nowPrograms
    }


    override fun attachAdapter(adapter: NowAdapter) {
        this.adapter = adapter
        rvNow.layoutManager = LinearLayoutManager(this)
        rvNow.adapter = this.adapter
        this.adapter.notifyDataSetChanged()
    }


}