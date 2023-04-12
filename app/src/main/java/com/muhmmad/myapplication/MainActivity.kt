package com.muhmmad.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.muhmmad.myapplication.databinding.ActivityMainBinding
import com.muhmmad.rowcalendar.calendar.CalendarAdapter
import com.muhmmad.rowcalendar.calendar.CalendarViewManager
import com.muhmmad.rowcalendar.utils.DateHelper
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            val calendarViewManager = object :
                CalendarViewManager {
                override fun setCalendarResource(isToday: Boolean, isSelected: Boolean): Int {
                    return if (isToday) R.layout.today_calendar_item
                    else if (isSelected) R.layout.selected_calendar_item
                    else R.layout.calendar_item
                }

                override fun bindData(
                    holder: CalendarAdapter.ViewHolder,
                    date: Date,
                    position: Int,
                    isSelected: Boolean
                ) {
                    holder.itemView.findViewById<TextView>(R.id.tv_date_calendar_item).text =
                      DateHelper.getDay(date)

                    holder.itemView.findViewById<TextView>(R.id.tv_day_calendar_item).text =
                     DateHelper.getDayLetter(date)

                }
            }


            rowCalendar.init(calendarViewManager)
            rowCalendar.onClickListener {
                Toast.makeText(root.context, "click", Toast.LENGTH_SHORT).show()
            }
            rowCalendar.onLongPressed {
                Toast.makeText(root.context, "LongPress", Toast.LENGTH_SHORT).show()
            }
        }
    }
}