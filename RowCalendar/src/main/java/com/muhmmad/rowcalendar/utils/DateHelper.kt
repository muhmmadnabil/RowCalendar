package com.muhmmad.rowcalendar.utils

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)

    init {
        simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT")
    }

    fun formatHour24(createdAt: String?): String {
        if (createdAt.isNullOrEmpty())
            return ""
        try {
            val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            return dateFormat.format(simpleDateFormat.parse(createdAt))
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return createdAt
    }

    fun formatHour12(createdAt: String?): String {
        if (createdAt.isNullOrEmpty())
            return ""
        try {
            val dateFormat = SimpleDateFormat("hh:mm a", Locale.US)
            dateFormat.timeZone = TimeZone.getDefault()
            return dateFormat.format(simpleDateFormat.parse(createdAt))
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return createdAt
    }

    fun formatDate(createdAt: String?): String {
        if (createdAt.isNullOrEmpty())
            return ""
        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            return dateFormat.format(simpleDateFormat.parse(createdAt))
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return createdAt
    }

    fun formatDateAndTime(createdAt: String?): String {
        if (createdAt.isNullOrEmpty())
            return ""
        try {
            val dateFormat = SimpleDateFormat("EEEE ,dd MMMM yyyy  hh:mm a", Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            return dateFormat.format(simpleDateFormat.parse(createdAt))
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return createdAt
    }

    fun formatMatchDate(createdAt: String?):String{
        if (createdAt.isNullOrEmpty())
            return ""
        try {
            val dateFormat = SimpleDateFormat("dd-MM-yy hh:mm a", Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            return dateFormat.format(simpleDateFormat.parse(createdAt))
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return createdAt
    }

    fun getDayName(date: Date): String {
        val text = android.text.format.DateFormat.format("EE", date)
        return text.toString()
    }

    fun getDay(date: Date): String {
        val text = android.text.format.DateFormat.format("dd", date)
        return text.toString()
    }

    fun getDayLetter(date: Date): String {
        val text = android.text.format.DateFormat.format("EE", date)
        return text[0].toString()
    }

    fun getMonthName(date: Date): String {
        val text = android.text.format.DateFormat.format("MMMM", date)
        return text.toString()
    }
}