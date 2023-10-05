package com.example.mym_posdemomvvm.moduls.periodicAlarmManager

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.mym_posdemomvvm.R
import com.example.mym_posdemomvvm.moduls.mposPoc.ui.NotificationBroadcast
import com.example.mym_posdemomvvm.moduls.mposPoc.ui.activities.MainActivity
import com.example.mym_posdemomvvm.utils.SharedPrefs
import com.example.mym_posdemomvvm.utils.Utils
import com.example.mym_posdemomvvm.utils.Utils.TIMESTAMP
import com.example.mym_posdemomvvm.utils.Utils.getFormattedDate
import java.lang.Exception
import java.util.Date
import java.util.concurrent.TimeUnit


class PeriodicManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_periodic_alarm_manager)
        setRecyclerView()
        initView()
    }

    private fun initView() {
        findViewById<Button>(R.id.btn_start_alarm).setOnClickListener {
            val t = findViewById<EditText>(R.id.et_time).let {
                it.text.toString().ifEmpty { (10).toString() }
            }
            startAlarm(t)
            startWorkManager(t)
        }
        findViewById<Button>(R.id.btn_stop_alarm).setOnClickListener {
            cancelAlarm()
            stopWork()
        }
    }

    private fun setRecyclerView() {
        val a = PeriodicAdapter()
        findViewById<RecyclerView>(R.id.rv_alarm_manager).apply {
            adapter = a
            layoutManager = LinearLayoutManager(this@PeriodicManagerActivity)
        }
        val s = SharedPrefs.getString(this, "ALARM_MANAGER")
        a.submitList(s.split("|"))

        val b = PeriodicAdapter()
        findViewById<RecyclerView>(R.id.rv_work_manager).apply {
            adapter = b
            layoutManager = LinearLayoutManager(this@PeriodicManagerActivity)
        }
        val s1 = SharedPrefs.getString(this, "WORK_MANAGER")
        b.submitList(s1.split("|"))
    }

    private fun startAlarm(t: String) {
        try {
            cancelAlarm()
            val firstMillis = System.currentTimeMillis() + (1000 * 60)// alarm is set right away
            Utils.showToast(this, Date(firstMillis).getFormattedDate(TIMESTAMP))
            val alarm = this.getSystemService(ALARM_SERVICE) as AlarmManager
            alarm.setExact(
                AlarmManager.RTC_WAKEUP,
                firstMillis,
//                t.toLong() * 1000L,
                getPendingIntentForAlarm()
            )
            Utils.showToast(this, "AM started")
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun cancelAlarm() {
        val alarm = this.getSystemService(ALARM_SERVICE) as AlarmManager
        alarm.cancel(getPendingIntentForAlarm())
        Utils.showToast(this, "AM stoped")
    }

    private fun getPendingIntentForAlarm(): PendingIntent? {
        val intent = Intent(this, MyAlarmReceiver::class.java)
        return PendingIntent.getBroadcast(
            this,
            MyAlarmReceiver.REQUEST_CODE,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    class MyAlarmReceiver : BroadcastReceiver() {
        // Triggered by the Alarm periodically (starts the service to run task)
        override fun onReceive(it: Context, intent: Intent) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    NotificationBroadcast.NEW_NOTIFICATION_CHANNEL_ID,
                    NotificationBroadcast.NEW_NOTIFICATION_CHANNEL_ID,
                    NotificationManager.IMPORTANCE_HIGH
                )
                channel.lightColor = Color.BLUE
                channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
                channel.description = NotificationBroadcast.NEW_NOTIFICATION_CHANNEL_ID
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
                    NotificationCompat.Builder(it,
                        NotificationBroadcast.NEW_NOTIFICATION_CHANNEL_ID
                    )
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


//                val s = System.currentTimeMillis() + (1000 * 60)
//                println("------------>$s")
//                (it.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager).setExact(
//                    AlarmManager.RTC_WAKEUP,
//                    s,
//                    getPenningIntent(it)
//                )
            }
            val s = SharedPrefs.getString(it, "ALARM_MANAGER") + "|" + Date().getFormattedDate(
                TIMESTAMP
            )
            SharedPrefs.setString(it, "ALARM_MANAGER", s)
        }

        companion object {
            const val REQUEST_CODE = 12345
            const val ACTION = "com.codepath.example.servicesdemo.alarm"
        }
    }

    private fun startWorkManager(t: String) {
        try {
            val workerRequest = PeriodicWorkRequestBuilder<BackgroundWorker>(t.toLong() * 1000, TimeUnit.MILLISECONDS)
                .addTag(BackgroundWorker::class.java.simpleName)
                .build()

            WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                "BackgroundWorker",
                ExistingPeriodicWorkPolicy.KEEP,
                workerRequest
            )
            Utils.showToast(this, "WM started")
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun stopWork(){
        Utils.showToast(this, "WM stopped")
        WorkManager.getInstance(this)
            .cancelAllWorkByTag(BackgroundWorker::class.java.simpleName)
    }


}