package com.example.mym_posdemomvvm.moduls.mposPoc.ui

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.mym_posdemomvvm.R
import com.example.mym_posdemomvvm.moduls.mposPoc.ui.activities.MainActivity
import com.example.mym_posdemomvvm.utils.Constants
import com.example.mym_posdemomvvm.utils.SharedPrefs
import com.example.mym_posdemomvvm.utils.Utils


class NotificationBroadcast : BroadcastReceiver() {

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "Alarm Notification"
        const val NEW_NOTIFICATION_CHANNEL_ID = "NOTIFICATION_CHANNEL_ID"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        println("Alarm ----> Receive")
        context?.let {
            Utils.showToast(
                it,
                SharedPrefs.getString(context, Constants.AMOUNT_IN_NOTIFICATION)
            )
        }

        context?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    NEW_NOTIFICATION_CHANNEL_ID,
                    NEW_NOTIFICATION_CHANNEL_ID,
                    NotificationManager.IMPORTANCE_HIGH
                )
                channel.lightColor = Color.BLUE
                channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
                channel.description = NEW_NOTIFICATION_CHANNEL_ID
                val manager = it.getSystemService(NotificationManager::class.java)
                manager.createNotificationChannel(channel)

                val pendingIntentToCLickOnNotification: PendingIntent = PendingIntent.getActivity(
                    it,
                    100,
                    Intent(it, MainActivity::class.java),
                    PendingIntent.FLAG_IMMUTABLE
                )

                val largeIcon = BitmapFactory.decodeResource(
                    it.resources,
                    R.mipmap.ic_launcher_round
                )

                val remoteViews = RemoteViews(it.packageName, R.layout.custom_notification_layout)
                val style: NotificationCompat.Style = NotificationCompat.DecoratedCustomViewStyle()

                val notificationBuilder: NotificationCompat.Builder =
                    NotificationCompat.Builder(it, NEW_NOTIFICATION_CHANNEL_ID)
                notificationBuilder
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setLargeIcon(largeIcon)
                    .setContentTitle("Scheduled Notification")
                    .setContentText("Some big text Some big textSome big textSome big textSome big textSome big textSome big text")
                    .setColor(ContextCompat.getColor(it, R.color.cardview_dark_background))
                    .setCustomBigContentView(remoteViews)
                    .setStyle(style)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setOnlyAlertOnce(true)
                    .setCategory(Notification.CATEGORY_ALARM)
                    .setContentIntent(pendingIntentToCLickOnNotification)
                manager.notify(100, notificationBuilder.build())


                val s = System.currentTimeMillis() + (1000 * 60)
                println("------------>$s")
                (it.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager).setExact(
                    AlarmManager.RTC_WAKEUP,
                    s,
                    getPenningIntent(it)
                )
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getPenningIntent(context: Context): PendingIntent? {
        return PendingIntent.getBroadcast(
            context,
            1000,
            Intent(context, NotificationBroadcast::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
    }
}