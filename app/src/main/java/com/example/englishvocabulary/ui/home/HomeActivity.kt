package com.example.englishvocabulary.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseActivity
import com.example.englishvocabulary.databinding.ActivityHomeBinding
import com.google.android.material.tabs.TabLayout

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeTabList = resources.getStringArray(R.array.home_tab)

        binding.run {
            homePager.adapter = object : FragmentStatePagerAdapter(
                supportFragmentManager,
                BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            ) {
                override fun getCount(): Int =
                    homeTabList.size

                override fun getItem(position: Int): Fragment =
                    when (position) {
                        0 -> StudyFragment.newInstance()
                        1 -> QuizFragment.newInstance()
                        2 -> BookmarkFragment.newInstance()
                        3 -> SearchFragment.newInstance()
                        else -> error("Invalid position")
                    }

                override fun getPageTitle(position: Int): CharSequence? =
                    homeTabList[position]
            }

            bottomTab.run {
                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        val position = tab?.position
//                        if (position != null) {
//                            setToolbarTitle(homeTabList[position])
//                        }
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {
                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {
                    }
                })

                setupWithViewPager(homePager)
                getTabAt(0)?.setIcon(R.drawable.ic_study)
                getTabAt(1)?.setIcon(R.drawable.ic_quiz)
                getTabAt(2)?.setIcon(R.drawable.ic_bookmark)
                getTabAt(3)?.setIcon(R.drawable.ic_search)
            }
        }
    }

    companion object {
        fun getIntent(context: Context) =
            Intent(context, HomeActivity::class.java)
    }
}