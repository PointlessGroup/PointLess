package pointlessgroup.pointless.core

import java.util.*

class TimeRecord(val dateTimeProvider: DateTimeProvider) {

    private val value: ArrayList<Long> = ArrayList<Long>()

    fun register() {
        if (value.size > 1)
            value[1] = dateTimeProvider.currentTimeMilis()
        else
            value.add(dateTimeProvider.currentTimeMilis())

    }

    fun registerCount() = value.size

    fun getTimeRecord(): Long {

        if (value.isEmpty() || value.size != 2) {
            return 0
        } else {
            return value[1] - value[0]
        }
    }

}

interface DateTimeProvider {
    fun currentTimeMilis(): Long

}