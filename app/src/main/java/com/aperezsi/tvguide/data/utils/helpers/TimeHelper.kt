package com.aperezsi.tvguide.data.utils.helpers

import com.aperezsi.tvguide.data.utils.enums.TimerEnum
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class TimeHelper {

    fun epochToStringDate(epoch: String, format: String) : String {
        val sdf = SimpleDateFormat(format)
        return sdf.format(Date(epoch.toLong()))
    }

    fun currentTime(timerEnum: TimerEnum) : Date {
        return when (timerEnum){
            TimerEnum.DATE, TimerEnum.EPOCH -> Calendar.getInstance().time
            else -> Calendar.getInstance().time
        }
    }

}