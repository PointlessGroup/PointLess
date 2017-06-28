package pointlessgroup.pointless.core

import java.util.*

class TimeRecord(val dateTimeProvider: DateTimeProvider) {

    private val value: ArrayList<Long> = ArrayList<Long>()

    fun register() {
        value.add(dateTimeProvider.currentTimeMilis())
    }

    fun registerCount() = value.size

    fun getTimeRecord(): Long {

        if (value.isEmpty() || value.size < 2) {
            return 0;
        } else {
            return value.get(1) - value.get(0)
        }

    }
}

interface DateTimeProvider {
    fun currentTimeMilis(): Long

}