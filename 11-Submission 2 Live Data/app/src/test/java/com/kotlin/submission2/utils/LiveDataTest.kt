package com.kotlin.submission2.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
object LiveDataTest {

    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val countDownLatch = CountDownLatch(1)

        val observer = object : Observer<T> {
            override fun onChanged(t: T) {
                data[0] = t
                countDownLatch.countDown()
                liveData.removeObserver(this)
            }
        }
        liveData.observeForever(observer)
        try {
            countDownLatch.await(2, TimeUnit.SECONDS)
        } catch (e: Exception) {
            e.localizedMessage
        }

        return data[0] as T
    }

}