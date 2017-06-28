package pointlessgroup.pointless.core

class TimeRecord {

    private val value: ArrayList<Long> = ArrayList<Long>()

    fun register() {
        value.add(System.currentTimeMillis())
    }

    fun registerCount() = value.size
}