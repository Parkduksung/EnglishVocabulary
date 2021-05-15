package com.example.englishvocabulary.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseActivity
import com.example.englishvocabulary.databinding.ActivitySplashBinding
import com.example.englishvocabulary.ui.home.HomeActivity
import com.example.englishvocabulary.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {


    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(splashViewModel)

        Handler().postDelayed({
            startActivity(HomeActivity.getIntent(this))
            finish()
        }, SPLASH_DELAY_MILLIS)
    }


    override fun onDestroy() {
        lifecycle.removeObserver(splashViewModel)
        super.onDestroy()
    }

    companion object {
        private const val SPLASH_DELAY_MILLIS = 1500L
    }
}