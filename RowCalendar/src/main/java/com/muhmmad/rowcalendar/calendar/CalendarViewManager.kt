package com.muhmmad.rowcalendar.calendar

import java.util.*

interface CalendarViewManager {

    fun setCalendarResource(isToday: Boolean, isSelected: Boolean): Int

    fun bindData(holder: CalendarAdapter.ViewHolder, date: Date, position: Int, isSelected: Boolean)
}