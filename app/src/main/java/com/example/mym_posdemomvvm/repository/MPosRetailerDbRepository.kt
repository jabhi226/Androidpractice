package com.example.mym_posdemomvvm.repository

import android.app.Application
import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mym_posdemomvvm.daos.ManufactureDao
import com.example.mym_posdemomvvm.daos.MedicineDao
import com.example.mym_posdemomvvm.datalayer.MPOSDataLayer
import com.example.mym_posdemomvvm.models.Manufacture
import com.example.mym_posdemomvvm.models.Medicine
import com.example.mym_posdemomvvm.roomDb.RetailerDb
import java.util.*
import kotlin.collections.ArrayList

/**
 * Repo is a simple class which provide a clean API to access all dos.
 */
class MPosRetailerDbRepository(private val application: Application) {
    private val retailerDb = RetailerDb.getInstance(application)
    var medicineDoa: MedicineDao = retailerDb.medicineDao
    private var manufactureDao: ManufactureDao = retailerDb.manufactureDao

    private var allMedicine: LiveData<List<Medicine>>? = medicineDoa.getAllMedicines()
    private var allManufactures: LiveData<List<Manufacture>>? = manufactureDao.getAllManufactures()

    private var mPosDataLayer : MPOSDataLayer = MPOSDataLayer()
    var allMedicineContains: MutableLiveData<List<Medicine>>? = MutableLiveData()

//    init {
//        val retailerDb = RetailerDb.getInstance(application)
//        medicineDoa = retailerDb.medicineDao
//        manufactureDao = retailerDb.manufactureDao
//        allManufactures = manufactureDao.getAllManufactures()
//    }

    fun insert(medicine: Medicine) {
        Thread {
            Log.d("THREAD: ", "insert -> ${Thread.currentThread().id} -> ${Thread.currentThread().name}")
            medicineDoa.insert(medicine)
        }.start()
    }

    fun update(medicine: Medicine) {
        Thread {
            Log.d("THREAD: ", "update -> ${Thread.currentThread().id} -> ${Thread.currentThread().name}")
            medicineDoa?.update(medicine)
        }.start()
    }

    fun delete(medicine: Medicine) {
        Thread {
            Log.d("THREAD: ", "delete -> ${Thread.currentThread().id} -> ${Thread.currentThread().name}")
            medicineDoa?.delete(medicine)
        }.start()
    }

    fun updateMedicineStock(stock: Int, medicineId: Int) {
        Thread {
            Log.d("THREAD: ", "updateMedicineStock -> ${Thread.currentThread().id} -> ${Thread.currentThread().name}")
            medicineDoa?.updateMedicineStock(stock, medicineId)
        }.start()
    }

    fun medicineContains(name: String){
        Thread {
            Log.d("THREAD: ", "medicineContains -> ${Thread.currentThread().id} -> ${Thread.currentThread().name} : $name")
            Log.d("SALE_LOG", name)
//            allMedicineContains?.value = medicineDoa.getMedicinesContains(name)
//            onSaleListener?.showMedicineWithStock(allMedicineContains)
        }.start()
    }

    private val isMPos = true
//    fun getAllMedicinesContains(name: String): LiveData<List<Medicine>>?{
    fun getAllMedicinesContains(name: String): LiveData<List<Medicine>>? {
//        if (isMPos){
//            mPosDataLayer.getAllMedicineData() //mpos
//        } else {
//            mTerminalDataLAyer.getAllMEdicineData() //mTerminal
//        }
        var list: ArrayList<Medicine>? = ArrayList<Medicine>()

        Thread{
            list = medicineDoa.getMedicinesContains(name)?.value as ArrayList<Medicine>?

            val mH = Handler(application.mainLooper)
            val run = Runnable {
                Log.d("SALE_LOG_UPDATE", list?.size.toString())
                if (list != null){
                    allMedicineContains?.value = list!!
                }
            }
            mH.post(run)
        }.start()
        return allMedicineContains
    }

    @JvmName("getAllMedicines1")
    fun getAllMedicines(): LiveData<List<Medicine>>?{
        return allMedicine
    }

    fun insertManufacture(m: Manufacture){
        Thread{
            manufactureDao.insertManufacture(m)
        }.start()
    }

    fun updateManufacture(m: Manufacture){
        Thread{
            manufactureDao.updateManufacture(m)
        }.start()
    }

    fun deleteManufacture(m: Manufacture){
        Thread{
            manufactureDao.deleteManufacture(m)
        }.start()
    }

    fun deleteAllManufacture(){
        Thread{
            manufactureDao.deleteAllManufacture()
        }.start()
    }

    fun getAllManufactures(): LiveData<List<Manufacture>>?{
        return allManufactures
    }
}