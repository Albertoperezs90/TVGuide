package com.aperezsi.tvguide.data.ui.main.fragment.now

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.ui.base.BaseFragment
import com.aperezsi.tvguide.data.ui.main.data.NowAdapter
import kotlinx.android.synthetic.main.fragment_now.*

/**
 * Created by alberto on 06/05/2018.
 */
class NowFragment : BaseFragment(), NowContract.View {

    private val nowPresenter: NowContract.Presenter = NowPresenter(this)
    private lateinit var adapter: NowAdapter

    override fun getLayout(): Int = R.layout.fragment_now
    override fun getFragmentContext(): Context = context!!
    override fun getFragmentActivity(): FragmentActivity = activity!!

    override fun onStart() {
        super.onStart()
        nowPresenter.buildAdapter(R.layout.fragment_now_row)
    }

    override fun attachAdapter(adapter: NowAdapter) {
        this.adapter = adapter
        rvNow.layoutManager = LinearLayoutManager(context)
        rvNow.adapter = adapter
    }

    override fun notifyDataAdapterChanged() {
        adapter.notifyDataSetChanged()
    }
}