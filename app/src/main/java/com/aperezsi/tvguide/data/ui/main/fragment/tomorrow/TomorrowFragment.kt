package com.aperezsi.tvguide.data.ui.main.fragment.tomorrow

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.base.BaseFragment
import com.aperezsi.tvguide.data.ui.main.data.tomorrow.TomorrowAdapter
import com.aperezsi.tvguide.data.utils.helpers.TimeHelper
import kotlinx.android.synthetic.main.fragment_tomorrow.*

/**
 * Created by alberto on 06/05/2018.
 */
class TomorrowFragment : BaseFragment(), TomorrowContract.View {

    private val tomorrowPresenter: TomorrowPresenter = TomorrowPresenter(this)
    private lateinit var adapter: TomorrowAdapter

    override fun getLayout(): Int = R.layout.fragment_tomorrow
    override fun getFragmentActivity(): FragmentActivity = activity!!
    override fun getFragmentContext(): Context = context!!

    override fun onStart() {
        tomorrowPresenter.loadDataIntoPrograms(TimeHelper().getTomorrowEpochDate())
        refresh_tomorrow_container.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            tomorrowPresenter.refreshPrograms(TimeHelper().getTomorrowEpochDate())
        })
        super.onStart()
    }

    override fun buildAdapter() {
        tomorrowPresenter.buildAdapter(R.layout.fragment_tomorrow_row)
    }

    override fun attachAdapter(adapter: TomorrowAdapter) {
        this.adapter = adapter
        rvTomorrow.layoutManager = LinearLayoutManager(activity!!.baseContext)
        rvTomorrow.adapter = this.adapter
    }

    override fun notifyDataAdapterChanged() {
        adapter.notifyDataSetChanged()
    }

    override fun setContainerRefresh(flag: Boolean) {
        refresh_tomorrow_container.isRefreshing = flag
    }
}