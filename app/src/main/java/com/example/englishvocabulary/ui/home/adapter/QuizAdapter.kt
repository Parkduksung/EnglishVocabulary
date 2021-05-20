package com.example.englishvocabulary.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.ui.home.adapter.viewholder.QuizViewHolder
import com.example.englishvocabulary.ui.home.adapter.viewholder.VocaListener

class QuizAdapter : RecyclerView.Adapter<QuizViewHolder>() {

    private val vocaList = mutableListOf<List<ExcelData>>()

//    private lateinit var vocaListener: VocaListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder =
        QuizViewHolder(parent)


    override fun getItemCount(): Int =
        vocaList.size

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) =
        holder.bind(vocaList[position])


    fun addAllVocaData(list: List<List<ExcelData>>) {
        list.forEach {
            vocaList.add(it)
        }
        notifyDataSetChanged()
    }

    fun clear() {
        vocaList.clear()
        notifyDataSetChanged()
    }

//    fun setVocaItemClickListener(listener: VocaListener) {
//        vocaListener = listener
//    }
//
//    fun stateChangeBookmark(item: ExcelData) {
//        val index = vocaList.indexOf(item)
//        vocaList[index].like = !vocaList[index].like
//        notifyItemChanged(index)
//    }

}
