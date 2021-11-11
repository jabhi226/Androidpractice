package com.example.mym_posdemomvvm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import com.example.mym_posdemomvvm.databinding.ActivityMainBinding
import com.example.mym_posdemomvvm.fragments.AddMedicineFragment
import com.example.mym_posdemomvvm.fragments.ManufacturersFragment
import com.example.mym_posdemomvvm.fragments.SalesFragment
import com.example.mym_posdemomvvm.fragments.ShowAllMedicineFragment
import com.example.mym_posdemomvvm.utils.Constants
import com.example.mym_posdemomvvm.utils.Utils
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
//    private lateinit var medicineViewModel: MedicineViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initListener()
        getMeds()
    }

    private fun initViewModel() {
//        medicineViewModel = ViewModelProvider(this)[MedicineViewModel::class.java]
//        medicineViewModel.allMedicines?.observe(this, {
//            it.forEach { medicine ->
//                Log.e("MEDICINES", "getMeds: ${medicine.name}")
//            }
//        })
    }

    private fun getMeds() {
    }

    private fun initListener() {
        binding.sales.setOnClickListener(this)
        binding.purchase.setOnClickListener(this)
        binding.addMedicine.setOnClickListener(this)
        binding.showAllMedicines.setOnClickListener(this)
        binding.showMedicinesStock.setOnClickListener(this)
        binding.manufacture.setOnClickListener(this)
        binding.export.setOnClickListener(this)
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
                fm.replace(binding.mainFrame.id, ShowAllMedicineFragment(ShowAllMedicineFragment.SHOW_ALL_MEDICINE), "ShowAllMedicineFragment")
                fm.commit()
            }
            binding.showMedicinesStock.id -> {
                val fm = supportFragmentManager.beginTransaction()
                fm.replace(binding.mainFrame.id, ShowAllMedicineFragment(ShowAllMedicineFragment.SHOW_ALL_MEDICINE_STOCK), "ShowAllMedicineFragment")
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
        }
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
                val backupDBPath = "exportedDb.sqlite"      //you can modify the file type you need to export
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
        if (frag != null){
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
                if (num == it){
                    current++
                }
            }
            if (current > max){
                max = current
            }
        }
        return position.size - max
    }
}