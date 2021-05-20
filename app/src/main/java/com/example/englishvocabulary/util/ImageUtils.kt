package com.example.englishvocabulary.util

import android.animation.ValueAnimator.REVERSE
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation

object ImageUtils {
    fun blinkAnimation(duration: Long, repeatCount: Int = -1) = AlphaAnimation(0.0f, 1.0f).apply {
        this.duration = duration
        interpolator = AccelerateInterpolator()
        this.repeatCount = repeatCount //infinite = -1
        repeatMode = REVERSE
    }
}