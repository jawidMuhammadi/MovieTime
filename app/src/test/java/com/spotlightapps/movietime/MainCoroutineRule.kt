package com.spotlightapps.movietime

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Created by Ahmad Jawid Muhammadi
 * on 19-01-2022.
 */

@ExperimentalCoroutinesApi
class MainCoroutineRule : TestWatcher() {

    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    override fun starting(description: Description?) {
        Dispatchers.setMain(testDispatcher)
        super.starting(description)
    }

    override fun finished(description: Description?) {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        super.finished(description)
    }
}

@ExperimentalCoroutinesApi
fun <T> MainCoroutineRule.runBlockingTest(block: suspend CoroutineScope.() -> T) {
    testDispatcher.runBlockingTest {
        block()
    }
}