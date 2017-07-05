package pointlessgroup.pointless.core

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.util.*

// Objetivo, criar código para validar regra do ponto
// assumindo registro de ponto manual
@RunWith(JUnit4::class)
class ManualTimeRecordTest {

    lateinit var timeRecord: TimeRecord
    lateinit var mockDateTime: DateTimeProvider

    @Before fun initTimeRecord() {
        mockDateTime = Mockito.mock(DateTimeProvider::class.java)

        timeRecord = TimeRecord(mockDateTime);
    }

    @Test fun `when user uses the time record the time should be saved`() {
        timeRecord.register()

    }


    @Test fun `when register in and out, should return the difference between then`() {
        `when`(mockDateTime.currentTimeMilis()).thenReturn(10L)
        timeRecord.register()
        `when`(mockDateTime.currentTimeMilis()).thenReturn(45L)
        timeRecord.register()

        assertEquals(35, timeRecord.getTimeRecord())
    }

    @Test fun `should return zero when has only one record`() {
        timeRecord.register()

        assertEquals(0, timeRecord.getTimeRecord())
    }

    @Test fun `when input an hour different of zero should return the expect time `() {
        `when`(mockDateTime.currentTimeMilis()).thenReturn(5L)
        timeRecord.register()
        `when`(mockDateTime.currentTimeMilis()).thenReturn(65L)
        timeRecord.register()
        `when`(mockDateTime.currentTimeMilis()).thenReturn(90L)
        timeRecord.register()

        assertEquals(85, timeRecord.getTimeRecord())
    }

    @Test fun `when register might show dialog confirmation`() {
        `when`(mockDateTime.currentTimeMilis()).thenReturn(5L)
        timeRecord.register()

    }
}