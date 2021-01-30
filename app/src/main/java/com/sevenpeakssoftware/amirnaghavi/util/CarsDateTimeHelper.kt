package com.sevenpeakssoftware.amirnaghavi.util

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CarsDateTimeHelper @Inject constructor() {

    companion object{
        const val CURRENT_YEAR_DATE_TIME_FORMAT = "dd MM, HH:mm"
        const val DIFFERENT_YEAR_DATE_TIME_FORMAT = "dd MM yyyy, HH:mm"

    }

    fun formatDateTime(rawDateTime : Long): String =
        if(isCurrentYear(rawDateTime)){
            SimpleDateFormat(CURRENT_YEAR_DATE_TIME_FORMAT).format(Date(rawDateTime))
        }else {
            SimpleDateFormat(DIFFERENT_YEAR_DATE_TIME_FORMAT).format(Date(rawDateTime))
        }


    private fun isCurrentYear(rawDateTime: Long): Boolean =
        Date(rawDateTime).year == Calendar.getInstance().get(Calendar.YEAR)

}