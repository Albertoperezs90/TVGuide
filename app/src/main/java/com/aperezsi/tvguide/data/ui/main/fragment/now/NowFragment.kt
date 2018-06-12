package com.aperezsi.tvguide.data.ui.main.fragment.now

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.base.BaseFragment
import com.aperezsi.tvguide.data.ui.main.data.now.NowAdapter
import kotlinx.android.synthetic.main.fragment_now.*

@SuppressLint("ValidFragment")
/**
 * Created by alberto on 06/05/2018.
 */
class NowFragment(private val nowPrograms: List<ProgramResponse>) : BaseFragment(), NowContract.View {

    private val nowPresenter: NowContract.Presenter = NowPresenter(this)
    private lateinit var adapter: NowAdapter

    override fun getLayout(): Int = R.layout.fragment_now
    override fun getFragmentContext(): Context = context!!
    override fun getFragmentActivity(): FragmentActivity = activity!!

    override fun onStart() {
        super.onStart()
        nowPresenter.buildAdapter(R.layout.fragment_now_row)
        swipe_container.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            nowPresenter.refreshPrograms()
        })
    }

    override fun attachAdapter(adapter: NowAdapter) {
        this.adapter = adapter
        rvNow.layoutManager = LinearLayoutManager(context)
        rvNow.adapter = this.adapter
    }

    override fun notifyDataAdapterChanged() {
        adapter.notifyDataSetChanged()
    }

    override fun updatePrograms(filteredPrograms: List<ProgramResponse>) {
        nowPresenter.updatePrograms(filteredPrograms)
    }


    override fun getNowPrograms(): List<ProgramResponse> {
        return nowPrograms
    }

    override fun setContainerRefresh(flag: Boolean) {
       swipe_container.isRefreshing = flag
    }

    override fun refreshAdapter(programs: List<ProgramResponse>) {
        nowPresenter.updatePrograms(programs)
    }
}