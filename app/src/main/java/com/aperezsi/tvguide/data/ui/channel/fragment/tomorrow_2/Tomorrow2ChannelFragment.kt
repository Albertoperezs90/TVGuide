package com.aperezsi.tvguide.data.ui.channel.fragment.tomorrow_2

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.base.BaseFragment
import com.aperezsi.tvguide.data.ui.channel.data.tomorrow2.Tomorrow2ChannelAdapter
import kotlinx.android.synthetic.main.fragment_channel.*

@SuppressLint("ValidFragment")
/**
 * Created by alberto on 31/05/2018.
 */
class Tomorrow2ChannelFragment(val tomorrow2: List<ProgramResponse>?) : BaseFragment(), Tomorrow2ChannelContract.View {
    override fun refreshAdapter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var adapter: Tomorrow2ChannelAdapter
    private val tomorrow2ChannelPresenter = Tomorrow2ChannelPresenter(this)

    override fun getLayout(): Int = R.layout.fragment_channel

    override fun onStart() {
        rvFragmentChannel.layoutManager = LinearLayoutManager(context)
        adapter = tomorrow2ChannelPresenter.buildAdapter(R.layout.fragment_channel_row)
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
        return tomorrow2!!
    }
}