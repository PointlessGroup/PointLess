package pointlessgroup.pointless

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers
import org.junit.Test


class TimeRecordActivityTest {

    @Test fun whenCreated_ShouldShowTimerRecordButton() {
        onView(withId(R.id.btn_mainactivity_timerecord))
                .check(ViewAssertions.matches(isDisplayed()))
    }


}