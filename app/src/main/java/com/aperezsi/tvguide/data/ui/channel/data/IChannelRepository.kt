package com.aperezsi.tvguide.data.ui.channel.data

/**
 * Created by alberto on 31/05/2018.
 */
interface IChannelRepository {

    fun getChannelProgamming(idChannel: String)
}