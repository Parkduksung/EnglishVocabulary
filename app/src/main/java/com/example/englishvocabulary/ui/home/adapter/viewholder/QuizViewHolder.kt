package com.example.englishvocabulary.ui.home.adapter.viewholder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.englishvocabulary.App
import com.example.englishvocabulary.R
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.databinding.ItemQuizBinding

class QuizViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.item_quiz, parent, false
    )
) {
    private val binding = ItemQuizBinding.bind(itemView)

    fun bind(
        item: List<ExcelData>,
        shuffled : List<ExcelData>,
        listener: QuizItemListener
    ) {

        val resultItem = item[0]

//        val shuffleList = item.shuffled()

        binding.apply {
            word.text = resultItem.word

            mean1.text = shuffled[0].mean
            mean2.text = shuffled[1].mean
            mean3.text = shuffled[2].mean
            mean4.text = shuffled[3].mean

            mean1.setOnClickListener { mean1.checkAnswer(resultItem.mean) }
            mean2.setOnClickListener { mean2.checkAnswer(resultItem.mean) }
            mean3.setOnClickListener { mean3.checkAnswer(resultItem.mean) }
            mean4.setOnClickListener { mean4.checkAnswer(resultItem.mean) }
        }

        itemView.setOnLongClickListener {
            listener.getItem(resultItem)
            false
        }

    }

}

interface QuizItemListener {
    fun getItem(item: ExcelData)
}

@SuppressLint("ResourceAsColor")
fun TextView.checkAnswer(answer: String) {
    if (this.text == answer) {
        setBackgroundResource(R.drawable.shape_border_true)
        setTextColor(ContextCompat.getColor(App.instance.context(), R.color.white))
    } else {
        setBackgroundResource(R.drawable.shape_border_false)
        setTextColor(ContextCompat.getColor(App.instance.context(), R.color.white))
    }
}