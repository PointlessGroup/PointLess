package pointlessgroup.pointless.location

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices
import pointlessgroup.pointless.GeofenceTransitionsIntentService


class LocationApiLifecycleObserver(val context: Context) : LifecycleObserver,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private lateinit var mGoogleApiClient: GoogleApiClient

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        mGoogleApiClient = GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        mGoogleApiClient.connect()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        mGoogleApiClient.disconnect()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
    }

    override fun onConnected(p0: Bundle?) {
    }

    override fun onConnectionSuspended(p0: Int) {
    }

    fun lastKnowLocation(): Location? {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            return LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)
        return null
    }

    @SuppressLint("LongLogTag")
    fun addGeoFence() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            Log.w(TAG, "addGeoFence: Failed to add geofence", Exception())
            return
        }
        val radiusInMeters = 200F
        val geofence = Geofence.Builder()
                .setCircularRegion(WORK_LOCATION.first, WORK_LOCATION.second, radiusInMeters)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT)
                .build()
        val geofenceRequest = GeofencingRequest.Builder().addGeofence(geofence)
                .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                .build()

        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when
        // calling addGeofences() and removeGeofences().
        val pendingIntent = PendingIntent.getService(context, 0,
                Intent(context, GeofenceTransitionsIntentService::class.java), PendingIntent.FLAG_UPDATE_CURRENT)

        LocationServices.GeofencingApi.addGeofences(mGoogleApiClient, geofenceRequest, pendingIntent)
    }

    companion object {
        val TAG = "LocationApiLifecycleObserver"
        val WORK_LOCATION = Pair(-23.6014492, -46.6969882)
    }

}