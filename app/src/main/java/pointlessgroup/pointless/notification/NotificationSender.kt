package pointlessgroup.pointless.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import pointlessgroup.pointless.R

object NotificationSender {

    const val NOTIFICATION_ID = 123
    const val CHANNEL_ID = "POINTLESS"
    const val REQUEST_CODE = 1234
    const val ACTION_REGISTER = "ACTION_REGISTER"
    const val EXTRA_TIME_IN_MILLIS = "EXTRA_TIME_IN_MILLIS"

    fun sendNotificationBroadcast(context: Context) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Are you on your job?")
                .addAction(getInAction(context))
                .build()
        NotificationManagerCompat.from(context)
                .notify(NOTIFICATION_ID, notification)
    }

    private fun getInAction(context: Context) = NotificationCompat.Action(
            R.drawable.ic_getin_black_24dp, "Get in", getInBroadcastPendingIntent(context))

    private fun getInBroadcastPendingIntent(context: Context) = PendingIntent.getBroadcast(
            context, REQUEST_CODE, getInIntent(), PendingIntent.FLAG_ONE_SHOT)

    private fun getInIntent() = Intent(ACTION_REGISTER)
            .putExtra(EXTRA_TIME_IN_MILLIS, System.currentTimeMillis())
}
