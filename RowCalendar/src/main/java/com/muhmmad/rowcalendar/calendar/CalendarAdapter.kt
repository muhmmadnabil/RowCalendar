package com.muhmmad.rowcalendar.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class CalendarAdapter(
    private var list: ArrayList<com.muhmmad.rowcalendar.model.DateItem>,
    private var calendarViewManager: CalendarViewManager
) : RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    private var clickListener: ((Date) -> Unit)? = null
    private var longClickListener: ((Date) -> Unit)? = null
    private var multiSelection: Boolean = false

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewId = when (viewType) {
            SELECTED -> calendarViewManager.setCalendarResource(false, true)
            TODAY -> calendarViewManager.setCalendarResource(true, true)
            else -> calendarViewManager.setCalendarResource(false, false)
        }
        val itemView = LayoutInflater.from(parent.context).inflate(viewId, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (getItemViewType(position) == NOT_SELECTED) {
            calendarViewManager.bindData(holder, list[position].date, position, false)
            holder.itemView.setOnClickListener {
                itemClickListener(list[position])
            }
            holder.itemView.setOnLongClickListener {
                longItemClickListener(list[position])
                true
            }
        } else if (getItemViewType(position) == TODAY) {
            calendarViewManager.bindData(holder, list[position].date, position, false)
            holder.itemView.setOnClickListener {
                itemClickListener(list[position])
            }
            holder.itemView.setOnLongClickListener {
                longItemClickListener(list[position])
                true
            }
        } else {
            calendarViewManager.bindData(holder, list[position].date, position, true)
            holder.itemView.setOnClickListener {
                itemClickListener(list[position])
            }
            holder.itemView.setOnLongClickListener {
                longItemClickListener(list[position])
                true
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].isSelected)
            if (list[position].isToday)
                TODAY
            else
                SELECTED
        else
            NOT_SELECTED
    }

    fun setDates(list: List<com.muhmmad.rowcalendar.model.DateItem>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    private fun itemClickListener(item: com.muhmmad.rowcalendar.model.DateItem) {
        if (!multiSelection)
            list.forEach {
                it.isSelected = false
            }
        clickListener?.let { it(item.date) }
        item.isSelected = true
        notifyDataSetChanged()
    }

    private fun longItemClickListener(item: com.muhmmad.rowcalendar.model.DateItem) {
        longClickListener?.let {
            if (!multiSelection)
                list.forEach {
                    it.isSelected = false
                }
            it(item.date)
            item.isSelected = true
        }
        notifyDataSetChanged()
    }

    fun setClickListener(clickListener: ((Date) -> Unit)) {
        this.clickListener = clickListener
    }

    fun setLongPressListener(longClickListener: ((Date) -> Unit)) {
        this.longClickListener = longClickListener
    }

    fun setMultiSelection(multiSelection: Boolean) {
        this.multiSelection = multiSelection
    }

    companion object {
        const val SELECTED = 1
        const val NOT_SELECTED = 0
        const val TODAY = 2
    }
}