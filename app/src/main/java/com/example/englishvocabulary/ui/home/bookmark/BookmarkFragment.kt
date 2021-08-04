package com.example.englishvocabulary.ui.home.bookmark

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.databinding.FragmentBookmarkBinding
import com.example.englishvocabulary.ui.home.adapter.BookmarkAdapter
import com.example.englishvocabulary.ui.home.adapter.viewholder.BookmarkListener

class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(R.layout.fragment_bookmark),
    BookmarkListener {

    private val bookmarkAdapter by lazy { BookmarkAdapter() }

    private val bookmarkViewModel by viewModels<BookmarkViewModel>()

    private lateinit var renewBookmarkListener: RenewBookmarkListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as? RenewBookmarkListener)?.let {
            renewBookmarkListener = it
        }
    }

    override fun getItemClick(item: ExcelData) {
        bookmarkViewModel.deleteBookmark(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(bookmarkViewModel)

        bookmarkViewModel.bookmarkListLiveData.observe(viewLifecycleOwner, {
            bookmarkAdapter.addAllBookmarkData(it)
        })

        bookmarkViewModel.viewStateLiveData.observe(viewLifecycleOwner, {
            if (it is BookmarkViewModel.BookmarkViewState.RenewBookmarkAdapter) {
                renewBookmarkListener.renewItem(it.excelData)
                bookmarkAdapter.clear()
                bookmarkViewModel.getAllBookmark()
            }
        })
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

interface RenewBookmarkListener {
    fun renewItem(item: ExcelData)
}