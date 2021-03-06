package com.example.englishvocabulary.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.ui.adapter.viewholder.StudyViewHolder
import com.example.englishvocabulary.ui.adapter.viewholder.VocaListener

class DetailAdapter : RecyclerView.Adapter<StudyViewHolder>() {

    private val vocaList = mutableListOf<ExcelData>()

    private lateinit var vocaListener: VocaListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudyViewHolder =
        StudyViewHolder(parent)


    override fun getItemCount(): Int =
        vocaList.size

    override fun onBindViewHolder(holder: StudyViewHolder, position: Int) =
        holder.bind(vocaList[position], vocaListener)


    fun addAllVocaData(list: List<ExcelData>) {
        this.vocaList.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        vocaList.clear()
        notifyDataSetChanged()
    }

    fun setVocaItemClickListener(listener: VocaListener) {
        vocaListener = listener
    }

    fun stateChangeBookmark(item: ExcelData) {
        if(vocaList.contains(item)){
            val index = vocaList.indexOf(item)
            vocaList[index].like = !vocaList[index].like
            notifyItemChanged(index)
        }
    }

}
