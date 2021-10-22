package com.example.mym_posdemomvvm.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mym_posdemomvvm.interfaces.OnSaleListener
import com.example.mym_posdemomvvm.daos.MedicineDao
import com.example.mym_posdemomvvm.models.Medicine
import com.example.mym_posdemomvvm.roomDb.RetailerDb

class RetailerDbRepository(application: Application) {
    private var medicineDoa: MedicineDao? = null
    private var allMedicine: LiveData<List<Medicine>>? = null
//    private var medicineStock: LiveData<List<MedicineStock>>? = null
    private var allMedicineContains: LiveData<List<Medicine>>? = null

    init {
        val retailerDb = RetailerDb.getInstance(application)
        medicineDoa = retailerDb.medicineDao
        if (medicineDoa != null){
            allMedicine = medicineDoa!!.getAllMedicines()
        }
    }

    companion object {
        var onSaleListener: OnSaleListener? = null
    }

    fun insert(medicine: Medicine) {
        Thread {
            Log.d("THREAD: ", "insert -> ${Thread.currentThread().id} -> ${Thread.currentThread().name}")
            medicineDoa?.insert(medicine)
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
            allMedicineContains = medicineDoa?.getMedicinesContains(name)
            onSaleListener?.showMedicineWithStock()
        }.start()
    }

    fun getAllMedicinesContains(): LiveData<List<Medicine>>?{
        return allMedicineContains
    }

    @JvmName("getAllMedicines1")
    fun getAllMedicines(): LiveData<List<Medicine>>?{
        return allMedicine
    }
}