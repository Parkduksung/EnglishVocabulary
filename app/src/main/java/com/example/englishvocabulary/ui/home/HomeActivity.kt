package com.example.englishvocabulary.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseActivity
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.databinding.ActivityHomeBinding
import com.example.englishvocabulary.ui.home.adapter.FragmentPagerAdapter
import com.example.englishvocabulary.ui.home.bookmark.BookmarkFragment
import com.example.englishvocabulary.ui.home.bookmark.RenewBookmarkListener
import com.example.englishvocabulary.ui.home.quiz.QuizFragment
import com.example.englishvocabulary.ui.home.search.SearchFragment
import com.example.englishvocabulary.ui.home.study.StudyFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home),
    RenewBookmarkListener {

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewPager()
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


    override fun renewItem(item: ExcelData) {
        supportFragmentManager.fragments.forEach {
            when (it) {
                is StudyFragment -> {
                    it.renewItem(item)
                }
            }
        }
    }


    companion object {

        private const val LIMIT_COUNT = 4

        fun getIntent(context: Context) =
            Intent(context, HomeActivity::class.java)
    }
}

