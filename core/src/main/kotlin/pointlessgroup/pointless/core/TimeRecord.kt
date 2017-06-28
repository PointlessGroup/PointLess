package pointlessgroup.pointless.core

class TimeRecord {
    var value: Long? = null

    fun register() {
        value = System.currentTimeMillis()
    }

}