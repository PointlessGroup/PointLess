package pointlessgroup.pointless.wifi

import android.content.Context
import android.content.SharedPreferences
import android.net.wifi.ScanResult
import android.preference.Preference
import android.preference.PreferenceManager
import android.support.v4.content.SharedPreferencesCompat

class Preferences(context: Context) {

    val preferences: SharedPreferences
//    lateinit var gson :

    init {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

//    var lastScan: List<ScanResult>
//        get() = preferences.get

    companion object {
        const val PREF_NAME = "wifi_settings"
    }


}