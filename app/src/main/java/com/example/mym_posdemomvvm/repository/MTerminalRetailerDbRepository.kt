//package com.example.mym_posdemomvvm.repository
//
//import android.app.Application
//import android.util.Log
//import androidx.lifecycle.LiveData
//import com.example.mym_posdemomvvm.daos.ManufactureDao
//import com.example.mym_posdemomvvm.daos.MedicineDao
//import com.example.mym_posdemomvvm.datalayer.MPOSDataLayer
//import com.example.mym_posdemomvvm.models.Manufacture
//import com.example.mym_posdemomvvm.models.Medicine
//import com.example.mym_posdemomvvm.roomDb.RetailerDb
//
//class MTerminalRetailerDbRepository(application: Application) {
//
//    private var allMedicine: LiveData<List<Medicine>>? = null
//    private var allManufactures: LiveData<List<Manufacture>>? = null
//
//    private var mPosDataLayer : MPOSDataLayer = MPOSDataLayer()
//    private var allMedicineContains: List<Medicine>? = null
//
//    init {
////        val retailerDb = RetailerDb.getInstance(application)
////        medicineDoa = retailerDb.medicineDao
////        manufactureDao = retailerDb.manufactureDao
////        allMedicine = medicineDoa.getAllMedicines()
////        allManufactures = manufactureDao.getAllManufactures()
//    }
//
//    fun insert(medicine: Medicine) {
//        Thread {
//            RedbookApi
//        }.start()
//    }
//
//    fun update(medicine: Medicine) {
//        Thread {
//            Log.d("THREAD: ", "update -> ${Thread.currentThread().id} -> ${Thread.currentThread().name}")
//            medicineDoa?.update(medicine)
//        }.start()
//    }
//
//    fun delete(medicine: Medicine) {
//        Thread {
//            Log.d("THREAD: ", "delete -> ${Thread.currentThread().id} -> ${Thread.currentThread().name}")
//            medicineDoa?.delete(medicine)
//        }.start()
//    }
//
//    fun updateMedicineStock(stock: Int, medicineId: Int) {
//        Thread {
//            Log.d("THREAD: ", "updateMedicineStock -> ${Thread.currentThread().id} -> ${Thread.currentThread().name}")
//            medicineDoa?.updateMedicineStock(stock, medicineId)
//        }.start()
//    }
//
//    fun medicineContains(name: String){
//        Thread {
//            Log.d("THREAD: ", "medicineContains -> ${Thread.currentThread().id} -> ${Thread.currentThread().name} : $name")
//            Log.d("SALE_LOG", name)
//            allMedicineContains = medicineDoa.getMedicinesContains(name)
////            onSaleListener?.showMedicineWithStock(allMedicineContains)
//        }.start()
//    }
//
//    private val isMPos = true
//    fun getAllMedicinesContains(name: String): List<Medicine>?{
////        if (isMPos){
////            mPosDataLayer.getAllMedicineData() //mpos
////        } else {
////            mTerminalDataLAyer.getAllMEdicineData() //mTerminal
////        }
//        Thread{
//            allMedicineContains = medicineDoa.getMedicinesContains(name)
//        }.start()
//        return allMedicineContains
//    }
//
//    @JvmName("getAllMedicines1")
//    fun getAllMedicines(): LiveData<List<Medicine>>?{
//        return allMedicine
//    }
//
//    fun insertManufacture(m: Manufacture){
//        Thread{
//            manufactureDao.insertManufacture(m)
//        }.start()
//    }
//
//    fun updateManufacture(m: Manufacture){
//        Thread{
//            manufactureDao.updateManufacture(m)
//        }.start()
//    }
//
//    fun deleteManufacture(m: Manufacture){
//        Thread{
//            manufactureDao.deleteManufacture(m)
//        }.start()
//    }
//
//    fun deleteAllManufacture(){
//        Thread{
//            manufactureDao.deleteAllManufacture()
//        }.start()
//    }
//
//    fun getAllManufactures(): LiveData<List<Manufacture>>?{
//        return allManufactures
//    }
//
//}