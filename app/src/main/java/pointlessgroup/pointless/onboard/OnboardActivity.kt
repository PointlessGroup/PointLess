package pointlessgroup.pointless.onboard

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import pointlessgroup.pointless.PermissionHelper
import pointlessgroup.pointless.R
import pointlessgroup.pointless.databinding.ActivityOnboardBinding
import pointlessgroup.pointless.login.LoginActivity
import pointlessgroup.pointless.wifi.WifiManagerCompat

class OnboardActivity : AppCompatActivity() {

    lateinit var binding: ActivityOnboardBinding

    companion object {
        val TAG = "OnboardActivity"
        const val RC_ACCESS_FINE_LOCATION = 190
        const val RC_CHANGE_WIFI_STATE = 191
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_onboard, null, false)
        setContentView(binding.root)
        binding.next.setOnClickListener(this::onNextClick)
    }

    override fun onResume() {
        super.onResume()
        if (!PermissionHelper.isPermissionGranted(this, Manifest.permission.ACCESS_FINE_LOCATION))
            PermissionHelper.requestPermission(this, Manifest.permission.ACCESS_FINE_LOCATION, RC_ACCESS_FINE_LOCATION)

        if (!PermissionHelper.isPermissionGranted(this, Manifest.permission.CHANGE_WIFI_STATE))
            PermissionHelper.requestPermission(this, Manifest.permission.CHANGE_WIFI_STATE, RC_CHANGE_WIFI_STATE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val startScan = WifiManagerCompat.from(this).startScan()
            Log.d(TAG, "onRequestPermissionsResult: " + startScan)
        }
    }

    fun onNextClick(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
