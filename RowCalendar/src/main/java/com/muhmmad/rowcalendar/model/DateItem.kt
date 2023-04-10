package com.muhmmad.rowcalendar.model

import java.util.*

data class DateItem(
    var isToday: Boolean = false,
    var isSelected: Boolean = false,
    val date: Date
)