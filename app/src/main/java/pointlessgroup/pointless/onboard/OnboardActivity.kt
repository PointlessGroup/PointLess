package pointlessgroup.pointless.onboard

import android.Manifest
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import pointlessgroup.pointless.PermissionHelper
import pointlessgroup.pointless.R
import pointlessgroup.pointless.databinding.ActivityOnboardBinding
import pointlessgroup.pointless.login.LoginActivity

class OnboardActivity : AppCompatActivity() {

    lateinit var binding: ActivityOnboardBinding

    companion object {
        const val RC_ACCESS_FINE_LOCATION = 190
        const val RC_CHANGE_WIFI_STATE = 191
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate<ActivityOnboardBinding>(LayoutInflater.from(this), R.layout.activity_onboard, null, false)
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

    fun onNextClick(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
