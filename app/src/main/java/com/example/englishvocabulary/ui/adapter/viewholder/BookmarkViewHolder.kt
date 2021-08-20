package com.example.englishvocabulary.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.englishvocabulary.R
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.databinding.ItemBookmarkBinding

class BookmarkViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.item_bookmark, parent, false
    )
) {
    private val binding = ItemBookmarkBinding.bind(itemView)

    fun bind(
        item: ExcelData,
        itemClickListener: BookmarkListener
    ) {

        binding.apply {
            word.text = item.word
            mean.text = item.mean

            bookmark.setOnClickListener {
                itemClickListener.getItemClick(item)
            }
        }

    }

}

interface BookmarkListener {
    fun getItemClick(item: ExcelData)
}