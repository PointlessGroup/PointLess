package pointlessgroup.pointless.core

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito
import java.util.*

// Objetivo, criar código para validar regra do ponto
// assumindo registro de ponto manual
@RunWith(JUnit4::class)
class ManualTimeRecordTest {

    lateinit var timeRecord: TimeRecord

    @Before fun initTimeRecord() {
        Mockito.mock(DateTimeProvider::class.java)

        timeRecord = TimeRecord(mock)
    }

    @Test fun `when user uses the time record the time should be saved`() {
        timeRecord.register()
        assertEquals(1, timeRecord.registerCount())
    }

    @Test fun `when user register time record many times should save all`() {
        timeRecord.register()
        timeRecord.register()
        timeRecord.register()
        timeRecord.register()
        timeRecord.register()
        assertEquals(5, timeRecord.registerCount())
    }


    @Test fun `when register in and out, should return the difference between then`() {
        timeRecord.register()
        Thread.sleep(10)
        timeRecord.register()

        assertEquals(10, timeRecord.getTimeRecord())
    }

}