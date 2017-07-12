package pointlessgroup.pointless

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TimeRecordActivityTest {

    @JvmField @Rule val rule = ActivityTestRule<TimeRecordActivity>(TimeRecordActivity::class.java, true, true)

    @Test fun whenCreated_ShouldShowTimer() {
        onView(withText("00:00"))
                .check(matches(isDisplayed()))
    }

    @Test fun whenClickRecord_shouldStartTimer() {
        onView(withId(R.id.btn_mainactivity_timerecord))
                .perform(click())

        onView(withText("00:01"))
                .check(matches(isDisplayed()))
    }

    @Test fun whenClickRecordTwice_shouldStopTimer() {
        onView(withId(R.id.btn_mainactivity_timerecord))
                .perform(click())

        onView(withId(R.id.btn_mainactivity_timerecord))
                .perform(click())

        onView(withText("00:01"))
                .check(matches(isDisplayed()))

    }

}