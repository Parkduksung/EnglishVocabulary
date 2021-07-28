package com.example.englishvocabulary.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseActivity
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.databinding.ActivityHomeBinding
import com.example.englishvocabulary.ui.home.bookmark.BookmarkFragment
import com.example.englishvocabulary.ui.home.bookmark.RenewBookmarkListener
import com.example.englishvocabulary.ui.home.quiz.QuizFragment
import com.example.englishvocabulary.ui.home.search.SearchFragment
import com.example.englishvocabulary.ui.home.study.StudyFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home),
    RenewBookmarkListener {


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

            val nameArray = resources.getStringArray(R.array.array_tab_name).toList()
            val iconArray = resources.getIntArray(R.array.array_tab_icon).toList()

            val tabInfoArray = (nameArray zip iconArray)

            tab.text = tabInfoArray[position].first
            tab.setIcon(tabInfoArray[position].second)
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

class FragmentPagerAdapter(
    private val fragmentList: List<Fragment>,
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = fragmentList.size
    override fun createFragment(position: Int) = fragmentList[position]
}
