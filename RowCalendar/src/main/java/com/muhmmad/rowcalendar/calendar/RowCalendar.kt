package com.muhmmad.rowcalendar.calendar

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muhmmad.mylibrary.R
import java.util.*
import kotlin.collections.ArrayList

class RowCalendar(context: Context, attrs: AttributeSet) : RecyclerView(context, attrs) {
    private var includeCurrentDate: Boolean
    private var multiSelection: Boolean
    private var longPress: Boolean
    private var currentMonth = 0
    private var position = 0
    private val calendar = Calendar.getInstance()
    private lateinit var calendarAdapter: CalendarAdapter

    init {
        itemAnimator = null
        context.theme.obtainStyledAttributes(attrs, R.styleable.RowCalendar, 0, 0).apply {
            try {
                includeCurrentDate =
                    getBoolean(R.styleable.RowCalendar_includeCurrentDate, false)
                multiSelection = getBoolean(R.styleable.RowCalendar_multiSelection, false)
                longPress = getBoolean(R.styleable.RowCalendar_longPress, false)
            } finally {
                recycle()
            }
        }
        calendar.time = Date()
        currentMonth = calendar[Calendar.MONTH]
    }

    fun init(calendarViewManager: CalendarViewManager) {
        this.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            val list = getDates()
            if (includeCurrentDate) {
                list.forEachIndexed { index, dateItem ->
                    if (dateItem.date.date == Date().date) {
                        dateItem.isToday = true
                        dateItem.isSelected = true
                        position = index
                    }
                }
            }

            calendarAdapter = CalendarAdapter(list, calendarViewManager)
            calendarAdapter.setMultiSelection(multiSelection)
            adapter = calendarAdapter
            this.scrollToPosition(position)
        }
    }

    fun getNextMonth() {
        currentMonth++
        if (currentMonth == 12) {
            calendar.set(Calendar.YEAR, calendar[Calendar.YEAR] + 1)
            currentMonth = 0
        }
        calendarAdapter.setDates(getDates())
    }

    fun getPreviousMonth() {
        currentMonth--
        if (currentMonth == -1) {
            calendar.set(Calendar.YEAR, calendar[Calendar.YEAR] - 1)
            currentMonth = 11
        }

        calendarAdapter.setDates(getDates())
    }

    private fun getDates(): ArrayList<com.muhmmad.rowcalendar.model.DateItem> {
        val list = ArrayList<com.muhmmad.rowcalendar.model.DateItem>()
        calendar.set(Calendar.MONTH, currentMonth)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        list.add(com.muhmmad.rowcalendar.model.DateItem(date = calendar.time))
        while (currentMonth == calendar[Calendar.MONTH]) {
            calendar.add(Calendar.DATE, +1)
            if (calendar[Calendar.MONTH] == currentMonth)
                list.add(com.muhmmad.rowcalendar.model.DateItem(date = calendar.time))
        }
        calendar.add(Calendar.DATE, -1)

        return list
    }

    fun onClickListener(clickListener: (date: Date) -> Unit) {
        calendarAdapter.setClickListener(clickListener)
    }

    fun onLongPressed(longClickListener: ((Date) -> Unit)) {
        if (longPress)
            calendarAdapter.setLongPressListener(longClickListener)
        else
            throw IllegalArgumentException("the value of long Press is false to use it make it true")
    }

    fun setIncludeCurrentDate(includeCurrentDate: Boolean) {
        this.includeCurrentDate = includeCurrentDate
    }

    fun setLongPress(longPress: Boolean) {
        this.longPress = longPress
    }

    fun setMultiSelection(multiSelection: Boolean) {
        this.multiSelection = multiSelection
        calendarAdapter.setMultiSelection(multiSelection)
    }

}