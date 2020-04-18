package com.mitchmele.ksquared.utils

import com.mitchmele.ksquared.algo_store.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.rules.TestWatcher

@ExperimentalCoroutinesApi
class CoroutineTestRule(
    val testCoroutineDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
): TestWatcher() {

    val testDispatcherProvider = object : DispatcherProvider {
        override val io: CoroutineDispatcher
            get() = testCoroutineDispatcher
        override val ui: CoroutineDispatcher
            get() = testCoroutineDispatcher
        override val default: CoroutineDispatcher
            get() = testCoroutineDispatcher
        override val unconfined: CoroutineDispatcher
            get() = testCoroutineDispatcher

    }
}