package gts.bitfinex

import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.assertion.ViewAssertions

import gts.bitfinex.presentation.BitfinexActivity

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BitfinexActivityTest {

    @Test
    fun givenNothing_WhenLaunchApp_ThenShowRightScreen() {
        ActivityScenario.launch(BitfinexActivity::class.java)

        onView(ViewMatchers.withId(R.id.tickerLayout)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )

        onView(ViewMatchers.withId(R.id.orderBookHeader)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )

        onView(ViewMatchers.withId(R.id.list_layout)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }
}
