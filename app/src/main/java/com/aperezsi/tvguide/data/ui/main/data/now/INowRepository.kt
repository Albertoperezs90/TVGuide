package com.aperezsi.tvguide.data.ui.main.data.now

/**
 * Created by alberto on 31/05/2018.
 */
interface INowRepository {

    fun refreshPrograms()

    fun filterPrograms()
}