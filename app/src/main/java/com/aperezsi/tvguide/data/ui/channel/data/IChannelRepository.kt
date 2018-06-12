package com.aperezsi.tvguide.data.ui.channel.data

import com.aperezsi.tvguide.data.data.ChannelProgamming
import com.aperezsi.tvguide.data.data.ProgramResponse

/**
 * Created by alberto on 31/05/2018.
 */
interface IChannelRepository {

    fun getChannelProgramming(idChannel: String)
    fun sortChannelProgramming(channelProgamming: MutableList<ProgramResponse>)
}