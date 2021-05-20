package com.example.englishvocabulary.ui.home.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
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
        item: List<ExcelData>
    ) {

        val resultItem = item.shuffled()[0]

        val shuffleList = item.shuffled()

        binding.apply {
            word.text = resultItem.word

            mean1.text = shuffleList[0].mean
            mean2.text = shuffleList[1].mean
            mean3.text = shuffleList[2].mean
            mean4.text = shuffleList[3].mean

            mean1.setOnClickListener { mean1.checkAnswer(resultItem.mean) }
            mean2.setOnClickListener { mean2.checkAnswer(resultItem.mean) }
            mean3.setOnClickListener { mean3.checkAnswer(resultItem.mean) }
            mean4.setOnClickListener { mean4.checkAnswer(resultItem.mean) }

        }
    }

}

fun TextView.checkAnswer(answer: String) {

    if (this.text == answer) {
        Toast.makeText(App.instance.context(), "맞음.", Toast.LENGTH_LONG).show()
    } else {
        Toast.makeText(App.instance.context(), "틀림.", Toast.LENGTH_LONG).show()
    }
}