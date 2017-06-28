package pointlessgroup.pointless.core

class TimeRecord {

    var value: List<Long> = ArrayList<Long>()

    fun register() {
        value+(System.currentTimeMillis())
    }

    fun int registerCount(){
        return value.size();
    }



}