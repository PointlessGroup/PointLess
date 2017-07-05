package pointlessgroup.pointless.core

import java.util.*

class TimeRecord(val dateTimeProvider: DateTimeProvider) {

    private var startTime : Long? = null
    private var endTime : Long? = null

    fun register() {
        if (startTime == null){
            startTime = dateTimeProvider.currentTimeMilis()
            return
        }

        if (endTime!= null)
            throw InvalidRegisterException()

        endTime = dateTimeProvider.currentTimeMilis()
    }

    fun getTotalTime(): Long {
        return endTime!! - startTime!!
    }

}

interface DateTimeProvider {
    fun currentTimeMilis(): Long

}

class InvalidRegisterException : RuntimeException("Invalid register")
