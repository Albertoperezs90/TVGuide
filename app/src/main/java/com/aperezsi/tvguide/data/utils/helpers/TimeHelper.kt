package com.aperezsi.tvguide.data.utils.helpers

import android.annotation.TargetApi
import android.os.Build
import android.text.format.DateUtils
import android.util.Log
import com.aperezsi.tvguide.data.utils.enums.TimerEnum
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.DayOfWeek
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


        fun getTomorrowEpochDate() : String {
            val calendar = GregorianCalendar()
            calendar.time = Date(System.currentTimeMillis())
            calendar.add(Calendar.DATE, 1)
            calendar.set(Calendar.HOUR, 0)
            calendar.set(Calendar.MINUTE, 0)
            return (calendar.timeInMillis / 1000).toString()
        }

        fun getEpochDate(daysToAdd: Int = 0) : Long {
            val calendar = GregorianCalendar.getInstance()
            calendar.timeZone = TimeZone.getTimeZone("Europe/Madrid")
            calendar.time = Date(System.currentTimeMillis())
            calendar.add(Calendar.DATE, daysToAdd)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 1)
            val epoch = calendar.time
            return epoch.time / 1000
        }

        fun getDayByEpoch(epochStart: String) : String {
            val calendar = GregorianCalendar.getInstance()
            calendar.timeZone = TimeZone.getTimeZone("Europe/Madrid")
            calendar.time = Date(epochStart.toLong() * 1000)
            val day = calendar.get(Calendar.DAY_OF_MONTH).toString()
            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
            return "$day ${getDayOfTheWeekText(dayOfWeek)}"
        }


        @TargetApi(Build.VERSION_CODES.O)
        fun getDayOfTheWeekText(dayOfWeek: Int) : String{
            return when (dayOfWeek){
                1 -> "Dom"
                2 -> "Lun"
                3 -> "Mar"
                4 -> "Mie"
                5 -> "Jue"
                6 -> "Vie"
                7 -> "Sab"
                8 -> "Dom"
                else -> ""
            }
        }

}