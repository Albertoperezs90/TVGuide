package com.aperezsi.tvguide.data.ui.channel.fragment.today

import android.annotation.SuppressLint
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.base.BaseFragment

@SuppressLint("ValidFragment")
/**
 * Created by alberto on 31/05/2018.
 */
class TodayChannelFragment(today: List<ProgramResponse>?) : BaseFragment(), TodayChannelContract.View {

    override fun getLayout(): Int = R.layout.fragment_channel_row
}