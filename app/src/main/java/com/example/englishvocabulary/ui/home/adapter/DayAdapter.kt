package com.example.englishvocabulary.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.englishvocabulary.ui.home.adapter.viewholder.DayListener
import com.example.englishvocabulary.ui.home.adapter.viewholder.DayViewHolder

class DayAdapter : RecyclerView.Adapter<DayViewHolder>() {

    private val dayList = mutableListOf<String>()

    private lateinit var dayListener: DayListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder =
        DayViewHolder(parent)


    override fun getItemCount(): Int =
        dayList.size


    override fun onBindViewHolder(holder: DayViewHolder, position: Int) =
        holder.bind(dayList[position], dayListener)


    fun addAllDayData() {
        dayList.addAll(dayStringList)
        notifyDataSetChanged()
    }

    fun clear() {
        dayList.clear()
        notifyDataSetChanged()
    }


    fun setDayItemClickListener(listener: DayListener) {
        dayListener = listener
    }


    companion object {
        private val dayStringList = listOf(
            "Day1",
            "Day2",
            "Day3",
            "Day4",
            "Day5",
            "Day6",
            "Day7",
            "Day8",
            "Day9",
            "Day10",
            "Day11",
            "Day12",
            "Day13",
            "Day14",
            "Day15",
            "Day16",
            "Day17",
            "Day18",
            "Day19",
            "Day20",
            "Day21",
            "Day22",
            "Day23",
            "Day24",
            "Day25",
            "Day26",
            "Day27",
            "Day28",
            "Day29",
            "Day30",
        )
    }

}