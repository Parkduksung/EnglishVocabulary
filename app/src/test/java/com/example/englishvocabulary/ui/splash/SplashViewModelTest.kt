package com.example.englishvocabulary.ui.splash

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class SplashViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() : Unit = runBlocking{
        hiltRule.inject()
    }

    @Test
    fun start(){

    }
}