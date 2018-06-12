package com.aperezsi.tvguide.data.ui.channel.fragment.tomorrow_1

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.base.BaseFragment
import com.aperezsi.tvguide.data.ui.channel.data.tomorrow1.Tomorrow1ChannelAdapter
import kotlinx.android.synthetic.main.fragment_channel.*


@SuppressLint("ValidFragment")
/**
 * Created by alberto on 31/05/2018.
 */
class Tomorrow1ChannelFragment(val tomorrow1: List<ProgramResponse>?) : BaseFragment(), Tomorrow1ChannelContract.View {
    override fun refreshAdapter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var adapter: Tomorrow1ChannelAdapter
    private val tomorrow1ChannelPresenter = Tomorrow1ChannelPresenter(this)

    override fun getLayout(): Int = R.layout.fragment_channel

    override fun onStart() {
        rvFragmentChannel.layoutManager = LinearLayoutManager(context)
        adapter = tomorrow1ChannelPresenter.buildAdapter(R.layout.fragment_channel_row)
        rvFragmentChannel.adapter = adapter
        super.onStart()
    }

    override fun getFragmentActivity(): FragmentActivity {
        return activity!!
    }

    override fun getFragmentContext(): Context {
        return context!!
    }

    override fun refreshAdapter(programs: List<ProgramResponse>) {
        adapter.notifyDataSetChanged()
    }

    override fun getPrograms(): List<ProgramResponse> {
        return tomorrow1!!
    }
}