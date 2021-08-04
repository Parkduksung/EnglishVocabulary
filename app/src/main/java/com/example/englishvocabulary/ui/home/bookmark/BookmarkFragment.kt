package com.example.englishvocabulary.ui.home.bookmark

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.databinding.FragmentBookmarkBinding
import com.example.englishvocabulary.ui.home.HomeViewModel
import com.example.englishvocabulary.ui.home.adapter.BookmarkAdapter
import com.example.englishvocabulary.ui.home.adapter.viewholder.BookmarkListener

class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(R.layout.fragment_bookmark),
    BookmarkListener {

    private val bookmarkAdapter by lazy { BookmarkAdapter() }

    private val bookmarkViewModel by viewModels<BookmarkViewModel>()

    private val homeViewModel by activityViewModels<HomeViewModel>()

    override fun getItemClick(item: ExcelData) {
        homeViewModel.toggleBookmark(isBookmarked = false, item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(bookmarkViewModel)

        startBookmarkAdapter()

        bookmarkViewModel.bookmarkListLiveData.observe(viewLifecycleOwner, {
            bookmarkAdapter.addAllBookmarkData(it)
        })

        homeViewModel.viewStateLiveData.observe(requireActivity()) { homeViewState ->
            when (homeViewState) {
                is HomeViewModel.HomeViewState.ToggleBookMark -> {
                    bookmarkAdapter.clear()
                    bookmarkViewModel.getAllBookmark()
                }
            }
        }
    }

    private fun startBookmarkAdapter() {
        binding.bookmarkRv.run {
            adapter = bookmarkAdapter
            bookmarkAdapter.clear()
            layoutManager = LinearLayoutManager(requireContext())
            bookmarkAdapter.setBookmarkItemClickListener(this@BookmarkFragment)
        }
        bookmarkViewModel.getAllBookmark()
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
