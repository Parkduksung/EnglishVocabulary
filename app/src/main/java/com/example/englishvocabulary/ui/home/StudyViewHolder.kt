package com.example.englishvocabulary.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.englishvocabulary.R
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.databinding.ItemStudyBinding

class StudyViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.item_study, parent, false
    )
) {
    private val binding = ItemStudyBinding.bind(itemView)

    fun bind(
        item: ExcelData,
        itemClickListener: VocaListener
    ) {

        itemView.setOnClickListener {
            itemClickListener.getItemClick(item)
        }

        binding.apply {
            day.text = item.day
            word.text = item.word
            mean.text = item.mean
        }
    }

}

interface VocaListener {
    fun getItemClick(item: ExcelData)
}