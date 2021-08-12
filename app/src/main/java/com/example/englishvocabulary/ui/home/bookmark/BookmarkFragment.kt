package com.example.englishvocabulary.ui.home.bookmark

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.base.ViewState
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
        homeViewModel.toggleBookmark(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initUi()
        initViewModel()

    }

    private fun initUi() {
        startBookmarkAdapter()
    }

    private fun initViewModel() {
        lifecycle.addObserver(bookmarkViewModel)

        homeViewModel.viewStateLiveData.observe(viewLifecycleOwner) { viewState: ViewState? ->
            (viewState as? HomeViewModel.HomeViewState)?.let { onChangedHomeViewState(viewState) }

        }

        bookmarkViewModel.viewStateLiveData.observe(viewLifecycleOwner) { viewState: ViewState? ->
            (viewState as? BookmarkViewModel.BookmarkViewState)?.let {
                onChangedBookmarkViewState(viewState)
            }
        }
    }


    private fun onChangedBookmarkViewState(viewState: BookmarkViewModel.BookmarkViewState) {
        when (viewState) {
            is BookmarkViewModel.BookmarkViewState.BookmarkList -> {
                bookmarkAdapter.addAllBookmarkData(viewState.bookmarkList)
            }
        }
    }


    private fun onChangedHomeViewState(viewState: HomeViewModel.HomeViewState) {
        when (viewState) {
            is HomeViewModel.HomeViewState.AddBookmark -> {
                bookmarkAdapter.addBookmark(viewState.excelData)
            }
            is HomeViewModel.HomeViewState.DeleteBookmark -> {
                bookmarkAdapter.deleteBookmark(viewState.excelData)
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
        super.onDestroy()
    }

    companion object {
        fun newInstance() =
            BookmarkFragment()
    }
}

