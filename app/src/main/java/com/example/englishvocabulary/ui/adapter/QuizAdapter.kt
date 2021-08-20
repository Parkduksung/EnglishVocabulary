package com.example.englishvocabulary.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.ui.adapter.viewholder.QuizItemListener
import com.example.englishvocabulary.ui.adapter.viewholder.QuizViewHolder

class QuizAdapter : RecyclerView.Adapter<QuizViewHolder>() {

    private val vocaList = mutableListOf<List<ExcelData>>()

    private val shuffledVocaList = mutableListOf<List<ExcelData>>()

    private lateinit var quizItemListener: QuizItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder =
        QuizViewHolder(parent)

    override fun getItemCount(): Int =
        vocaList.size

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.apply {
            setIsRecyclable(false)
            bind(vocaList[position],shuffledVocaList[position], quizItemListener)
        }
    }

    fun addAllVocaData(list: List<List<ExcelData>>) {
        vocaList.addAll(list)
        shuffledVocaList.addAll(list.map { it.shuffled() })
        notifyDataSetChanged()
    }

    fun clear() {
        vocaList.clear()
        shuffledVocaList.clear()
        notifyDataSetChanged()
    }

    fun setVocaItemClickListener(listener: QuizItemListener) {
        quizItemListener = listener
    }
//
//    fun stateChangeBookmark(item: ExcelData) {
//        val index = vocaList.indexOf(item)
//        vocaList[index].like = !vocaList[index].like
//        notifyItemChanged(index)
//    }

}
