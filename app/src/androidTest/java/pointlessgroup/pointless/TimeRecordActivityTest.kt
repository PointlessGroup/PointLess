package pointlessgroup.pointless

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.LayoutAssertions.noOverlaps
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

        sleep(1.minute())

        onView(withText("00:01"))
                .check(matches(isDisplayed()))
    }

    @Test fun whenClickRecordTwice_shouldStopTimer() {
        val view = onView(withId(R.id.btn_mainactivity_timerecord))
        view.perform(click())

        sleep(2.minutes())

        view.perform(click())

        onView(withText("00:02"))
                .check(matches(isDisplayed()))
        onView(withText(("07:58")))
                .check(matches(isDisplayed()))
    }

    @Test fun shouldNotOverlapViews() {
        onView(withId(R.id.container))
                .check(noOverlaps())
    }

}