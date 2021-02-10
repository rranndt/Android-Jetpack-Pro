package com.kotlin.myidlingresource

import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Rizki Rian Anandita
 * Create By rizki
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityInstrumentTest {

    private lateinit var instrumentalContext: Context

    @get:Rule
    val mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        instrumentalContext = InstrumentationRegistry.getInstrumentation().targetContext

        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun checkText() {
        onView(withId(R.id.text_view)).check(matches(withText(mActivityRule.activity.getString(R.string.prepare))))
        onView(withText(mActivityRule.activity.getString(R.string.start))).perform(click())

//        try {
//            Thread.sleep(2000)
//        } catch (e: InterruptedException) {
//            e.printStackTrace()
//        }
        onView(withId(R.id.text_view)).check(matches(withText(mActivityRule.activity.getString(R.string.delay2))))
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

}