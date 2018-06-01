package com.aperezsi.tvguide.data.ui.main.data.tomorrow

/**
 * Created by alberto on 31/05/2018.
 */
interface ITomorrowRepository {

    fun getTomorrowPrograms(epoch: String)
    fun refreshPrograms(epoch: String)
    fun filterPrograms()
}