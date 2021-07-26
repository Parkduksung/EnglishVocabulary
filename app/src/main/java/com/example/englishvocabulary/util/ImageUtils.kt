package com.example.englishvocabulary.util

import android.animation.ValueAnimator.REVERSE
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation

// 시작할때 애니메이션때 사용.
object ImageUtils {
    fun blinkAnimation(duration: Long = 1500L, repeatCount: Int = -1) = AlphaAnimation(0.0f, 1.0f).apply {
        this.duration = duration
        interpolator = AccelerateInterpolator()
        this.repeatCount = repeatCount //infinite = -1
        repeatMode = REVERSE
    }
}