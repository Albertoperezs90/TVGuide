package com.aperezsi.tvguide.data.ui.channel.fragment.tomorrow

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.base.BaseFragment
import com.aperezsi.tvguide.data.ui.channel.data.tomorrow.TomorrowChannelAdapter
import kotlinx.android.synthetic.main.fragment_channel.*

@SuppressLint("ValidFragment")
/**
 * Created by alberto on 31/05/2018.
 */
class TomorrowChannelFragment(val tomorrow: List<ProgramResponse>?) : BaseFragment(), TomorrowChannelContract.View {

    private lateinit var adapter: TomorrowChannelAdapter
    private val tomorrowChannelPresenter = TomorrowChannelPresenter(this)

    override fun getLayout(): Int = R.layout.fragment_channel

    override fun onStart() {
        rvFragmentChannel.layoutManager = LinearLayoutManager(context)
        adapter = tomorrowChannelPresenter.buildAdapter(R.layout.fragment_channel_row)
        rvFragmentChannel.adapter = adapter
        super.onStart()
    }

    override fun getFragmentActivity(): FragmentActivity {
        return activity!!
    }

    override fun getFragmentContext(): Context {
        return context!!
    }

    override fun refreshAdapter() {
        adapter.notifyDataSetChanged()
    }

    override fun getPrograms(): List<ProgramResponse> {
        return tomorrow!!
    }
}