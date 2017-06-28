package pointlessgroup.pointless.core

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import org.junit.Assert.*
import org.junit.Before

// Objetivo, criar código para validar regra do ponto
// assumindo registro de ponto manual
@RunWith(JUnit4::class)
class ManualTimeRecordTest {

    lateinit var timeRecord: TimeRecord

    @Before fun initTimeRecord() {
        timeRecord = TimeRecord()
    }

    @Test fun `when user uses de time record the time should be saved`() {
        timeRecord.register()
        assertNotNull(timeRecord.value)
    }

    @Test fun `when `() {

    }
}