package com.example.englishvocabulary.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.englishvocabulary.ui.home.adapter.viewholder.DayListener
import com.example.englishvocabulary.ui.home.adapter.viewholder.DayViewHolder

class DayAdapter : RecyclerView.Adapter<DayViewHolder>() {

    private val dayList = mutableListOf<String>().apply {
        for (i in 1..30) {
            add("Day${i}")
        }
    }

    private lateinit var dayListener: DayListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder =
        DayViewHolder(parent)


    override fun getItemCount(): Int =
        dayList.size


    override fun onBindViewHolder(holder: DayViewHolder, position: Int) =
        holder.bind(dayList[position], dayListener)


    fun setDayItemClickListener(listener: DayListener) {
        dayListener = listener
    }

}