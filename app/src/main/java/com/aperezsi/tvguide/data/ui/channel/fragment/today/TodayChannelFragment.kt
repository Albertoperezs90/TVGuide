package com.aperezsi.tvguide.data.ui.channel.fragment.today

import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.ui.base.BaseFragment

/**
 * Created by alberto on 31/05/2018.
 */
class TodayChannelFragment : BaseFragment(), TodayChannelContract.View {

    override fun getLayout(): Int = R.layout.fragment_channel_row
}