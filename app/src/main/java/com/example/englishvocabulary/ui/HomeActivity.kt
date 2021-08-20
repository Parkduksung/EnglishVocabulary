package com.example.englishvocabulary.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseActivity
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.databinding.ActivityHomeBinding
import com.example.englishvocabulary.ui.adapter.FragmentPagerAdapter
import com.example.englishvocabulary.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewPager()
        initViewModel()
    }


    private fun initViewModel() {
        homeViewModel.viewStateLiveData.observe(this) { viewState: ViewState? ->
            (viewState as? HomeViewModel.HomeViewState)?.let { onChangedViewState(viewState) }
        }
    }

    private fun onChangedViewState(viewState: HomeViewModel.HomeViewState) {
        when (viewState) {

            is HomeViewModel.HomeViewState.Error -> {
                Toast.makeText(this, viewState.errorMessage, Toast.LENGTH_SHORT).show()
            }

            is HomeViewModel.HomeViewState.AddBookmark -> {
                Toast.makeText(this, "즐겨찾기가 추가되었습니다.", Toast.LENGTH_SHORT).show()
            }

            is HomeViewModel.HomeViewState.DeleteBookmark -> {
                Toast.makeText(this, "즐겨찾기가 제거되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }


    /**
     * 첫 화면 구성 (ViewPager/TabLayout)
     */
    @SuppressLint("WrongConstant")
    private fun initViewPager() {


        with(binding) {

            val fragmentList = listOf(
                StudyFragment.newInstance(), QuizFragment.newInstance(),
                BookmarkFragment.newInstance(), SearchFragment.newInstance()
            )

            homePager.apply {
                adapter = FragmentPagerAdapter(fragmentList, this@HomeActivity)
                offscreenPageLimit = LIMIT_COUNT
                isUserInputEnabled = false
            }

            TabLayoutMediator(bottomTab, homePager, tabConfigurationStrategy).attach()

        }
    }

    private val tabConfigurationStrategy =
        TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = resources.getStringArray(R.array.array_tab_name)[position]
            tab.icon = resources.obtainTypedArray(R.array.array_tab_icon).getDrawable(position)
        }


    companion object {

        private const val LIMIT_COUNT = 4

        fun getIntent(context: Context) =
            Intent(context, HomeActivity::class.java)
    }
}

