package com.aperezsi.tvguide.data.ui.channel

import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.ui.base.BaseActivity

class ChannelActivity : BaseActivity(), ChannelContract.View {

    private val channelPresenter = ChannelPresenter(this)

    override fun getContentResource(): Int = R.layout.activity_channel
    override fun setFragmentNavigation() = channelPresenter.setNavigation(supportFragmentManager)
}