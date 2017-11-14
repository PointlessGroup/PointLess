package pointlessgroup.pointless.wifi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.util.Log


class WifiScanReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != ACTION)
            return
        Log.d(TAG, "onReceive: " + intent.extras)
        intent.extras.keySet().forEach {
            Log.d(TAG, "onReceive: -key: " + it)
            Log.d(TAG, "onReceive:   -  " + intent.extras[it])

        }

        val scanResults = WifiManagerCompat.from(context).scanResults
        Log.d(TAG, "onReceive: scanResults " + scanResults.size)
        scanResults.forEach {
            Log.d(TAG, "onReceive: result - " + it)
        }
//        Preferences(context).
    }

    fun register(context: Context) {
        context.registerReceiver(this, IntentFilter(ACTION))
    }

    fun unregister(context: Context) {
        context.unregisterReceiver(this)
    }

    companion object {
        val TAG = "WifiScanReceiver"
        const val ACTION = WifiManager.SCAN_RESULTS_AVAILABLE_ACTION
    }
}
