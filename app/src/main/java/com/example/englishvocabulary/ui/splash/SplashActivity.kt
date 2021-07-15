package com.example.englishvocabulary.ui.splash

import android.os.Bundle
import androidx.activity.viewModels
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseActivity
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.databinding.ActivitySplashBinding
import com.example.englishvocabulary.ui.home.HomeActivity
import com.example.englishvocabulary.util.ImageUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {


    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()

    }

    private fun initViewModel() {
        splashViewModel.viewStateLiveData.observe(this, { viewState: ViewState? ->
            (viewState as? SplashViewModel.SplashViewState)?.let { onChangedViewState(viewState) }
        })
    }

    private fun onChangedViewState(viewState: SplashViewModel.SplashViewState) = when (viewState) {
        SplashViewModel.SplashViewState.RouteMain -> startSplashAndRoute()
    }


    // 화면 전환
    private fun startSplashAndRoute() {
        GlobalScope.launch {
            binding.splashContainer.startAnimation(
                ImageUtils.blinkAnimation(duration = SPLASH_DELAY_MILLIS)
            )
            delay(SPLASH_DELAY_MILLIS)
            startActivity(HomeActivity.getIntent(this@SplashActivity))
            finish()
            this@SplashActivity.finish()
        }
    }


    companion object {
        private const val SPLASH_DELAY_MILLIS = 1500L
    }
}