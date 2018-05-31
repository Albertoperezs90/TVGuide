package com.aperezsi.tvguide.data.ui.channel.fragment

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.ui.base.BaseFragment
import com.aperezsi.tvguide.data.ui.channel.data.ChannelAdapter
import kotlinx.android.synthetic.main.fragment_channel.*

class ChannelFragment : BaseFragment(), ChannelFragmentContract.View {

    private val channelPresenter = ChannelFragmentPresenter(this)
    private lateinit var adapter: ChannelAdapter

    override fun getFragmentActivity(): FragmentActivity = activity!!
    override fun getFragmentContext(): Context  = activity!!.baseContext
    override fun getLayout(): Int = R.layout.fragment_channel

    override fun buildAdapter() {
        channelPresenter.buildAdapter(R.layout.fragment_channel_row)
    }

    override fun attachAdapter(adapter: ChannelAdapter) {
        this.adapter = adapter
        rvChannel.layoutManager = LinearLayoutManager(context)
        rvChannel.adapter = this.adapter
    }

    override fun notifyAdapterChange() {
        adapter.notifyDataSetChanged()
    }
}