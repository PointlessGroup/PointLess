package pointlessgroup.pointless.core

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
        return (endTime ?: dateTimeProvider.currentTimeMilis()) - startTime!!
    }

}

interface DateTimeProvider {
    fun currentTimeMilis(): Long

}

class InvalidRegisterException : IllegalArgumentException("Invalid register")
