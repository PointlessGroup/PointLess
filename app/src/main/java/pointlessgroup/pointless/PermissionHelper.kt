package pointlessgroup.pointless

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

object PermissionHelper {

    fun isPermissionGranted(context: Context, permission: String) = ContextCompat
            .checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

    // may be removed
    fun isExplanationNeeded(activity: Activity, permission: String) = ActivityCompat
            .shouldShowRequestPermissionRationale(activity, permission)

    fun requestPermission(activity: Activity, permission: String, requestCode: Int) = ActivityCompat
            .requestPermissions(activity, arrayOf(permission), requestCode)

}