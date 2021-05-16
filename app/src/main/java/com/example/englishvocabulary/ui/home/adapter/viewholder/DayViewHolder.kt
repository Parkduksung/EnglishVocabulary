package com.example.englishvocabulary.ui.home.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.englishvocabulary.R
import com.example.englishvocabulary.databinding.ItemDayBinding

class DayViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.item_day, parent, false
    )
) {
    private val binding = ItemDayBinding.bind(itemView)

    fun bind(
        item: String,
        itemClickListener: DayListener
    ) {

        itemView.setOnClickListener {
            itemClickListener.getItemClick(item)
        }

        binding.apply {
            day.text = item
        }
    }

}

interface DayListener {
    fun getItemClick(item: String)
}