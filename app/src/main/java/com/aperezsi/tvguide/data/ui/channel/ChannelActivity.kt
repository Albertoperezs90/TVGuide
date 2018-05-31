package com.aperezsi.tvguide.data.ui.channel

import android.content.Context
import android.os.Bundle
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
        channelPresenter.loadData()
        super.onStart()
    }
    override fun extractIdChannel(): String {
        return intent.getStringExtra("idChannel")
    }
}