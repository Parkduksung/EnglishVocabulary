package com.example.englishvocabulary.ui.splash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseActivity
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.databinding.ActivitySplashBinding
import com.example.englishvocabulary.ui.home.HomeActivity
import com.example.englishvocabulary.util.ImageUtils
import dagger.hilt.android.AndroidEntryPoint

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
        SplashViewModel.SplashViewState.SplashAnimation -> startSplashAnimation()
        SplashViewModel.SplashViewState.RouteMain -> startRouteMain()
        SplashViewModel.SplashViewState.Error -> startError()
    }


    private fun startSplashAnimation() {
        binding.splashContainer.startAnimation(
            ImageUtils.blinkAnimation()
        )
    }

    private fun startRouteMain() {
        startActivity(
            HomeActivity.getIntent(this@SplashActivity).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
        )
    }

    private fun startError() {
        Toast.makeText(
            this@SplashActivity,
            "잠시후 시도해 주세요..",
            Toast.LENGTH_LONG
        ).show()
    }
}