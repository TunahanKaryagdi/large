package com.tunahankaryagdi.b_log.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateHelper {

    fun convertToDate(dateString: String) : Date?{
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        return inputFormat.parse(dateString)
    }

    fun calculateDateDifference(dateString: String) : String{

        val date = convertToDate(dateString) ?: Date()
        val currentDate = Date()
        val differenceMilLis = currentDate.time - date.time
        val toDay = 1000 * 60 * 60 * 24
        val toHour = 1000 * 60 * 60
        val day = (differenceMilLis / toDay)
        return if (day>0){
            "$day day ago"
        }
        else{
            val hour = ((differenceMilLis % toDay) / toHour)
            "$hour hour ago"
        }
    }

}