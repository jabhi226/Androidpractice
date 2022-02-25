package com.example.mym_posdemomvvm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.mym_posdemomvvm.activities.MainActivity
import com.example.mym_posdemomvvm.utils.Constants
import com.example.mym_posdemomvvm.utils.SharedPrefs
import com.example.mym_posdemomvvm.utils.Utils


class NotificationBroadcast : BroadcastReceiver() {

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "Alarm Notification"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        println("Alarm ----> Receive")
        context?.let { Utils.showToast(it, SharedPrefs.getString(context, Constants.AMOUNT_IN_NOTIFICATION)) }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && context != null) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_ID,
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.lightColor = Color.BLUE
            channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            channel.description = NOTIFICATION_CHANNEL_ID
            val manager = context.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)

            val pendingIntentToCLickOnNotification: PendingIntent = PendingIntent.getActivity(
                context, 100, Intent(context, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE
            )

            val notificationBuilder: NotificationCompat.Builder =
                NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            notificationBuilder
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle("Daily Notification")
                .setContentInfo("This notification will repeat in each one minute.")
                .setColor(ContextCompat.getColor(context, android.R.color.holo_orange_light))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setOnlyAlertOnce(true)
                .setCategory(Notification.CATEGORY_ALARM)
                .setContentIntent(pendingIntentToCLickOnNotification)
            manager.notify(100, notificationBuilder.build())
        }
    }
}