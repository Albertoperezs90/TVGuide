package com.aperezsi.tvguide.data.ui.channel

import android.content.Context
import android.os.Bundle
import android.view.View
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.ui.base.BaseActivity
import com.aperezsi.tvguide.data.ui.base.BaseDelayedActivity
import kotlinx.android.synthetic.main.activity_channel.*

class ChannelActivity : BaseDelayedActivity(), ChannelContract.View {

    private val channelPresenter = ChannelPresenter(this)

    override fun getContentResource(): Int = R.layout.activity_channel
    override fun getActivityContext(): Context = this
    override fun setFragmentNavigation() = channelPresenter.setNavigation(supportFragmentManager, tabsChannel, viewpagerChannel)

    override fun onStart() {
        startProgressView()
        channelPresenter.loadData()
        super.onStart()
    }
    override fun extractIdChannel(): String {
        return intent.getStringExtra("idChannel")
    }

    override fun startProgressView() {
        gray_delayed_view.visibility = View.VISIBLE
        gray_delayed_view.animate().alpha(0.2F)
        channelProgress.isIndeterminate = true
        channelProgress.visibility = View.VISIBLE
    }

    override fun endProgressView() {
        gray_delayed_view.animate().alpha(0.0F)
        gray_delayed_view.visibility = View.GONE
        channelProgress.visibility = View.GONE
    }
}