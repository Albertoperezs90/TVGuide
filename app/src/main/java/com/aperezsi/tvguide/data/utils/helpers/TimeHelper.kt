package com.aperezsi.tvguide.data.utils.helpers

import android.text.format.DateUtils
import android.util.Log
import com.aperezsi.tvguide.data.utils.enums.TimerEnum
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class TimeHelper {


        fun epochToStringDate(epoch: String, format: String) : String {
            val sdf = SimpleDateFormat(format)
            val calendar = GregorianCalendar()
            calendar.time = Date(epoch.toLong() * 1000)
            return sdf.format(calendar.time)
        }

        fun currentTime(timerEnum: TimerEnum) : Date {
            return when (timerEnum){
                TimerEnum.DATE, TimerEnum.EPOCH -> Calendar.getInstance().time
                else -> Calendar.getInstance().time
            }
        }

        fun getCurrentSecondsEpoch() : Long {
            val calendar = GregorianCalendar()
            calendar.time = Date(System.currentTimeMillis())
            return calendar.timeInMillis / 1000
        }


        fun getCurrentPercentage(epochStart: Long, epochEnd: Long): Int {
            val currentEpoch = getCurrentSecondsEpoch()
            val totalTime = epochEnd - epochStart
            val currentTime = epochEnd  - currentEpoch
            var progress = 100 - (currentTime * 100 / totalTime).toInt()
            if (progress > 100) progress = 100
            return progress
        }

}