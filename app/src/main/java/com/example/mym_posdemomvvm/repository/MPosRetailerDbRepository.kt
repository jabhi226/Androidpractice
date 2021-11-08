package com.example.mym_posdemomvvm.repository

import android.app.Application
import android.os.AsyncTask
import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.paging.*
import com.example.mym_posdemomvvm.daos.ManufactureDao
import com.example.mym_posdemomvvm.daos.MedicineDao
import com.example.mym_posdemomvvm.datalayer.MPOSDataLayer
import com.example.mym_posdemomvvm.models.Manufacture
import com.example.mym_posdemomvvm.models.Medicine
import com.example.mym_posdemomvvm.roomDb.RetailerDb
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.Callable
import javax.sql.DataSource

/**
 * Repo is a simple class which provide a clean API to access all dos.
 */
class MPosRetailerDbRepository(private val application: Application) {
    private val retailerDb = RetailerDb.getInstance(application)
    private var medicineDoa: MedicineDao = retailerDb.medicineDao
    private var manufactureDao: ManufactureDao = retailerDb.manufactureDao

    private var allMedicine: LiveData<List<Medicine>>? = medicineDoa.getAllMedicines()
    var allPagedMedicine: Flow<PagingData<Medicine>>? = null
    private var allManufactures: LiveData<List<Manufacture>>? = manufactureDao.getAllManufactures()

    private var mPosDataLayer : MPOSDataLayer = MPOSDataLayer()
    var allMedicineContains: MutableLiveData<List<Medicine>>? = MutableLiveData()

    init {
        allPagedMedicine = Pager(
            PagingConfig(
                pageSize = 50,
                enablePlaceholders = true,
                maxSize = 300
            )
        ) {
            medicineDoa.getAllPagedMedicines()
        }.flow

        allManufactures = manufactureDao.getAllManufactures()
    }

    fun insert(medicine: Medicine) {
        Thread {
            Log.d("THREAD: ", "insert -> ${Thread.currentThread().id} -> ${Thread.currentThread().name}")
            medicineDoa.insert(medicine)
        }.start()
    }

    fun update(medicine: Medicine) {
        Thread {
            Log.d("THREAD: ", "update -> ${Thread.currentThread().id} -> ${Thread.currentThread().name}")
            medicineDoa.update(medicine)
        }.start()
    }

    fun delete(medicine: Medicine) {
        Thread {
            Log.d("THREAD: ", "delete -> ${Thread.currentThread().id} -> ${Thread.currentThread().name}")
            medicineDoa.delete(medicine)
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

//        Thread{
//            list = medicineDoa.getMedicinesContains("%$name%")?.value as ArrayList<Medicine>?
//
//            val mH = Handler(application.mainLooper)
//            val run = Runnable {
//                Log.d("SALE_LOG_UPDATE", list?.size.toString())
//                if (list != null){
//                    allMedicineContains?.value = list!!
//                }
//            }
//            mH.post(run)
//        }.start()

        val list: List<Medicine> = medicineDoa.getMedicinesContains("%$name%")
        Log.d("SALE_LOG_UPDATE", list.size.toString() )
        allMedicineContains!!.value = list
        return allMedicineContains
    }

    @JvmName("getAllMedicines1")
    fun getAllMedicines(): LiveData<List<Medicine>>?{
        allMedicine?.observeForever { Log.d("MEDICINE_SIZE", it.size.toString()) }
        return allMedicine
    }

    fun getAllMedicinesFromPaging(): Flow<PagingData<Medicine>>?{
        return allPagedMedicine
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