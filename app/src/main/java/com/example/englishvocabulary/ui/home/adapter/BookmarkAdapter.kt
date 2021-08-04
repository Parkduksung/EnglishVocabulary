package com.example.englishvocabulary.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.ui.home.adapter.viewholder.BookmarkListener
import com.example.englishvocabulary.ui.home.adapter.viewholder.BookmarkViewHolder

class BookmarkAdapter : RecyclerView.Adapter<BookmarkViewHolder>() {

    private val bookmarkList = mutableListOf<ExcelData>()

    private lateinit var bookmarkListener: BookmarkListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder =
        BookmarkViewHolder(parent)


    override fun getItemCount(): Int =
        bookmarkList.size

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) =
        holder.bind(bookmarkList[position], bookmarkListener)


    fun addAllBookmarkData(list: List<ExcelData>) {
        this.bookmarkList.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        bookmarkList.clear()
        notifyDataSetChanged()
    }

    fun setBookmarkItemClickListener(listener: BookmarkListener) {
        bookmarkListener = listener
    }

    fun deleteBookmark(item: ExcelData) {
        if (bookmarkList.contains(item)) {
            bookmarkList.remove(item)
            notifyDataSetChanged()
        }
    }

    fun addBookmark(item: ExcelData) {
        bookmarkList.add(item)
        notifyItemChanged(bookmarkList.lastIndex)
    }

}
