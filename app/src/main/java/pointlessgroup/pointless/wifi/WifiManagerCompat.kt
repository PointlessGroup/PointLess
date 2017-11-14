package pointlessgroup.pointless.wifi

import android.content.Context
import android.net.wifi.WifiManager

object WifiManagerCompat {

    fun from(context: Context) = context.applicationContext
                    .getSystemService(android.content.Context.WIFI_SERVICE) as WifiManager
}