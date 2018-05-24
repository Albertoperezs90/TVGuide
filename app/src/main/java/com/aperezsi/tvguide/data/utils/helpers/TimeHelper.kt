package com.aperezsi.tvguide.data.utils.helpers

import android.text.format.DateUtils
import com.aperezsi.tvguide.data.utils.enums.TimerEnum
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class TimeHelper {


        fun epochToStringDate(epoch: String, format: String) : String {
            val sdf = SimpleDateFormat(format)
            return sdf.format(Date(epoch.toLong() * 1000))
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
            calendar.add(Calendar.HOUR, 1)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            return calendar.timeInMillis / 1000
        }

}