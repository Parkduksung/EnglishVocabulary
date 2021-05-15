package com.example.englishvocabulary.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseActivity
import com.example.englishvocabulary.databinding.ActivitySplashBinding
import com.example.englishvocabulary.ui.home.HomeActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, SPLASH_DELAY_MILLIS)
    }

    companion object {
        private const val SPLASH_DELAY_MILLIS = 1500L
    }
}