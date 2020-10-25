package com.sfjava.dkquakes

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.sfjava.dkquakes.ui.EarthquakeListAdapter
import com.sfjava.dkquakes.ui.MainActivity
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class EarthquakesInstrumentedTest {
    @Test
    fun verifyRunningTestApplication() {
        assertTrue(InstrumentationRegistry.getInstrumentation().componentName.className
            .equals("com.sfjava.dkquakes.MockApplicationTestRunner")
        )
        // assertTrue(InstrumentationRegistry.getInstrumentation().context.applicationInfo.className
        //     .equals("com.sfjava.dkquakes.DKQuakesTestApplication")
        // )
    }

    @Test
    fun givenListOfearthquakesWhenAnItemIsClickedTheDetailMapisShown() {
        // val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.earthquakes_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<EarthquakeListAdapter.EarthquakeViewHolder>(
                    0,
                    ViewActions.click()
                )
            )

        onView(withId(R.id.map)).check(matches(isDisplayed()))
    }
}