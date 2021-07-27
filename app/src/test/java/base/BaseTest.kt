package base

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.englishvocabulary.koin.KoinBaseSetup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config

@Config(
        application = TestApplication::class
)
@RunWith(MockitoJUnitRunner::class)
abstract class BaseTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var application: Application

    @Mock
    lateinit var context: Context

    protected abstract fun createModules(): List<Module>

    private val koinSetup = object : KoinBaseSetup() {
        override fun getModules(): List<Module> {
            return createModules()
        }
    }

    @Before
    open fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        Mockito.`when`(context.applicationContext).thenReturn(context)
        koinSetup.setup(context)
    }

    @After
    open fun tearDown() {
        stopKoin()
    }
}
