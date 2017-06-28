package pointlessgroup.pointless.core

class TimeRecord(val dateTimeProvider: DateTimeProvider) {

    private val value: ArrayList<Long> = ArrayList<Long>()

    fun register() {
        value.add(System.currentTimeMillis())
    }

    fun registerCount() = value.size

    fun getTimeRecord(): Long {
        return value.get(0)
    }
}

interface DateTimeProvider {
    fun currentTimeMilis();
}