package com.example.englishvocabulary.ui.home.bookmark

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.databinding.FragmentBookmarkBinding
import com.example.englishvocabulary.ui.home.adapter.BookmarkAdapter
import com.example.englishvocabulary.ui.home.adapter.viewholder.BookmarkListener
import com.example.englishvocabulary.viewmodel.BookmarkViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(R.layout.fragment_bookmark),
    BookmarkListener {

    private val bookmarkAdapter by lazy { BookmarkAdapter() }

    private val bookmarkViewModel by viewModels<BookmarkViewModel>()

    override fun getItemClick(item: ExcelData) {
        bookmarkViewModel.deleteBookmark(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycle.addObserver(bookmarkViewModel)

        bookmarkViewModel.bookmarkListLiveData.observe(viewLifecycleOwner, {
            bookmarkAdapter.addAllBookmarkData(it)
        })

        binding.registerButton.setOnClickListener {
            bookmarkViewModel.getAddBookmark()
        }
    }

    override fun onResume() {
        startBookmarkAdapter()
        super.onResume()
    }

    private fun startBookmarkAdapter() {
        binding.bookmarkRv.run {
            adapter = bookmarkAdapter
            bookmarkAdapter.clear()
            layoutManager = LinearLayoutManager(requireContext())
            bookmarkAdapter.setBookmarkItemClickListener(this@BookmarkFragment)
        }
    }

    override fun onDestroy() {
        lifecycle.removeObserver(bookmarkViewModel)
        super.onDestroy()
    }

    companion object {
        fun newInstance() =
            BookmarkFragment()
    }
}