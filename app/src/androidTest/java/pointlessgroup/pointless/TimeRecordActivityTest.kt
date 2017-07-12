package pointlessgroup.pointless

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TimeRecordActivityTest {

    @JvmField @Rule val rule = ActivityTestRule<TimeRecordActivity>(TimeRecordActivity::class.java, true, true)

    @Test fun whenCreated_ShouldShowTimerRecordButton() {

        onView(withId(R.id.btn_mainactivity_timerecord))
                .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test fun whenRecordShould




}