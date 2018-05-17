package com.aperezsi.tvguide.data.ui.main.data

import com.aperezsi.tvguide.data.data.APIResponse

interface IProgramRepository {

    fun getNowPrograms()

    fun getTomorrowPrograms()

    fun getSchedulePrograms()
}