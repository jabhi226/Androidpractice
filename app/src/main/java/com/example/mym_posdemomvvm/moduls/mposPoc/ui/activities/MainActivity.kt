package com.example.mym_posdemomvvm.moduls.mposPoc.ui.activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.mym_posdemomvvm.moduls.mposPoc.ui.NotificationBroadcast
import com.example.mym_posdemomvvm.databinding.ActivityMainBinding
import com.example.mym_posdemomvvm.moduls.mposPoc.ui.fragments.AddMedicineFragment
import com.example.mym_posdemomvvm.moduls.mposPoc.ui.fragments.ManufacturersFragment
import com.example.mym_posdemomvvm.moduls.mposPoc.ui.fragments.SalesFragment
import com.example.mym_posdemomvvm.moduls.mposPoc.ui.fragments.ShowAllMedicineFragment
import com.example.mym_posdemomvvm.utils.Constants
import com.example.mym_posdemomvvm.utils.Utils
import com.example.mym_posdemomvvm.utils.Utils.TIMESTAMP
import com.example.mym_posdemomvvm.utils.Utils.getFormattedDate
import kotlinx.coroutines.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import kotlin.system.measureTimeMillis


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    //    private lateinit var medicineViewModel: MedicineViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        initViewModel()
        initListener()
        test()
    }

    private fun initViewModel() {
//        medicineViewModel = ViewModelProvider(this)[MedicineViewModel::class.java]
//        medicineViewModel.allMedicines?.observe(this, {
//            it.forEach { medicine ->
//                Log.e("MEDICINES", "getMeds: ${medicine.name}")
//            }
//        })
    }

    private fun test() {
        CoroutineScope(Dispatchers.Default).launch(Dispatchers.Unconfined) {
            val t = measureTimeMillis {
                val job: Deferred<Int> = async {
                    try {
                        launch {
                            for (i in 0..10) {
                                println("-----------------> $i     -> ${Thread.currentThread().name}")
                                delay(100)
                            }
//                        }
//                        launch {
                            for (i in 0..10) {
                                println("-----------------> $i     -> ${Thread.currentThread().name}")
                                delay(100)
//                                if (i == 10)
//                                    throw NullPointerException()
                            }
                        }
                        1
                    } catch (e: Exception) {
                        e.printStackTrace()
                        -1
                    }
                }

                println("Before     -> ${Thread.currentThread().name}")
                delay(50)
                println("job.isActive -> ${job.isActive} | ${job.isCancelled} | ${job.isCompleted}")
                val a = job.await()
                println("job.isActive -> ${job.isActive} | ${job.isCancelled} | ${job.isCompleted}")
                println("After     -> $a| ${Thread.currentThread().name}")
            }
            println("ITTOOK -> $t")//1126 - 2231
        }
    }


    private fun initListener() {
        binding.sales.setOnClickListener(this)
        binding.purchase.setOnClickListener(this)
        binding.addMedicine.setOnClickListener(this)
        binding.showAllMedicines.setOnClickListener(this)
        binding.showMedicinesStock.setOnClickListener(this)
        binding.manufacture.setOnClickListener(this)
        binding.export.setOnClickListener(this)
        binding.statusScreen.setOnClickListener(this)
        binding.setAlarm.setOnClickListener(this)
        binding.cancelAlarm.setOnClickListener(this)
        binding.shoNoti.setOnClickListener(this)
        binding.cancelNoti.setOnClickListener(this)
        binding.showEpoch.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.sales.id -> {
                val fm = supportFragmentManager.beginTransaction()
                fm.replace(binding.mainFrame.id, SalesFragment(), "SalesFragment")
                fm.commit()
            }
            binding.purchase.id -> {

            }
            binding.addMedicine.id -> {
                val fm = supportFragmentManager.beginTransaction()
                fm.replace(binding.mainFrame.id, AddMedicineFragment(), "AddMedicineFragment")
                fm.commit()
            }
            binding.showAllMedicines.id -> {
                val fm = supportFragmentManager.beginTransaction()
                fm.replace(
                    binding.mainFrame.id,
                    ShowAllMedicineFragment(ShowAllMedicineFragment.SHOW_ALL_MEDICINE),
                    "ShowAllMedicineFragment"
                )
                fm.commit()
            }
            binding.showMedicinesStock.id -> {
                val fm = supportFragmentManager.beginTransaction()
                fm.replace(
                    binding.mainFrame.id,
                    ShowAllMedicineFragment(ShowAllMedicineFragment.SHOW_ALL_MEDICINE_STOCK),
                    "ShowAllMedicineFragment"
                )
                fm.commit()
            }
            binding.manufacture.id -> {
                binding.activity.visibility = View.GONE
                val fm = supportFragmentManager.beginTransaction()
                fm.replace(binding.mainFrame.id, ManufacturersFragment(), "ManufacturersFragment")
                fm.commit()
            }
            binding.export.id -> {
                exportDatabase()
            }
            binding.statusScreen.id -> {
                val i = Intent(this, StatusActivity::class.java)
                this.startActivity(i)
            }
            binding.setAlarm.id -> {
//                notifyOnAlarm()
            }
            binding.cancelAlarm.id, binding.cancelNoti.id -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    (getSystemService(ALARM_SERVICE) as AlarmManager).cancel(getPenningIntent())
                }
            }
            binding.shoNoti.id -> {
                showNotification()
            }
            binding.showEpoch.id -> {
                val time = binding.time1.text.split(":")
                val c: Calendar = Calendar.getInstance()
                c.add(Calendar.HOUR, time[0].toInt())// 16
                c.set(Calendar.MINUTE, time[1].toInt())// 00
                binding.showTotalTime.setText(c.time.getFormattedDate(TIMESTAMP))
            }
        }
    }

    private fun showNotification() {
        val alarmManager = (getSystemService(ALARM_SERVICE) as AlarmManager)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.cancel(getPenningIntent())
        }
        val time = binding.time.text.split(":")
        val c: Calendar = Calendar.getInstance()
        c.add(Calendar.HOUR, time[0].toInt())
        c.set(Calendar.MINUTE, time[1].toInt())

        val oneMinuteLater = System.currentTimeMillis() + (1000 * 60)

        println("Calender time set --------> ${c.timeInMillis} | $oneMinuteLater")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                oneMinuteLater,
//                1000 * 60,
                getPenningIntent()
            )
        }

    }

//    private fun notifyOnAlarm() {
//        SharedPrefs.setString(this, Constants.AMOUNT_IN_NOTIFICATION, "10000.0")
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val c: Calendar = Calendar.getInstance()
//            c.add(Calendar.SECOND, 10)
//            (getSystemService(ALARM_SERVICE) as AlarmManager).setExact(
//                AlarmManager.RTC_WAKEUP,
//                c.timeInMillis,
//                getPenningIntent()
//            )
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getPenningIntent(): PendingIntent? {
        return PendingIntent.getBroadcast(
            this,
            1000,
            Intent(this, NotificationBroadcast::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun exportDatabase() {
        val sd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//        val sd = this.externalCacheDir

        // Get the Room database storage path using SupportSQLiteOpenHelper
        getDatabasePath(Constants.ROOM_DB_NAME).absolutePath
//        AppDatabase.getDatabase(applicationContext)!!.openHelper.writableDatabase.path

        if (sd != null) {
            Log.e("EXPORT: ", "sd -> ${sd.absolutePath}")
            if (sd.canWrite()) {
                val currentDBPath = getDatabasePath(Constants.ROOM_DB_NAME).absolutePath
                Log.e("EXPORT: ", "currentDBPath -> $currentDBPath")
                val backupDBPath =
                    "exportedDb.sqlite"      //you can modify the file type you need to export
                val currentDB = File(currentDBPath)
                Log.e("EXPORT: ", "currentDB -> ${currentDB.absolutePath}")
                val backupDB = File(sd, backupDBPath)
                Log.e("EXPORT: ", "backupDB -> ${backupDB.absolutePath}")
                if (currentDB.exists()) {
                    try {
                        val src = FileInputStream(currentDB).channel
                        val dst = FileOutputStream(backupDB).channel
                        dst.transferFrom(src, 0, src.size())
                        src.close()
                        dst.close()
                        Utils.showToast(this, "database stored in ${backupDB.absolutePath}")
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        } else {
            Utils.showToast(this, "sd is null")
        }
    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(binding.mainFrame.id)
        if (frag != null) {
            frag.let {
                when (it.javaClass.simpleName) {
                    "ShowAllMedicineFragment" -> {
                        val f = supportFragmentManager.findFragmentByTag("ShowAllMedicineFragment")
                        if (f != null) {
                            supportFragmentManager.beginTransaction().remove(f).commit()
                        }
                    }
                    "SalesFragment" -> {
                        val f = supportFragmentManager.findFragmentByTag("SalesFragment")
                        if (f != null) {
                            supportFragmentManager.beginTransaction().remove(f).commit()
                        }
                    }
                    "AddMedicineFragment" -> {
                        val f = supportFragmentManager.findFragmentByTag("AddMedicineFragment")
                        if (f != null) {
                            supportFragmentManager.beginTransaction().remove(f).commit()
                        }
                    }
                    "ManufacturersFragment" -> {
                        val f = supportFragmentManager.findFragmentByTag("ManufacturersFragment")
                        if (f != null) {
                            supportFragmentManager.beginTransaction().remove(f).commit()
                        }
                        binding.activity.visibility = View.VISIBLE
                    }
                    else -> {
                        super.onBackPressed()
                    }
                }
            }
        } else {
            super.onBackPressed()
        }
    }

    fun minCostToMoveChips1(position: IntArray): Int {
        var occ = 0
        position.distinct().forEach { num ->
            var a = 0
            position.sorted().forEach {
                if (it > num)
                    return@forEach
                if (it == num)
                    a++
            }
            print("$num | $a")
            if (a > occ)
                occ = a
            println(" | $occ")
        }
        return occ
    }

    fun minCostToMoveChips(position: IntArray): Int {
        var max = 0
        position.distinct().forEach { num ->
            var current = 0
            position.sortedArray().forEach {
                if (num == it) {
                    current++
                }
            }
            if (current > max) {
                max = current
            }
        }
        return position.size - max
    }
}