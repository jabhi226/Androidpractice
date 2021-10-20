package com.example.mym_posdemomvvm.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mym_posdemomvvm.daos.MedicineDao
import com.example.mym_posdemomvvm.models.Medicine
import com.example.mym_posdemomvvm.roomDb.RetailerDb

class RetailerDbRepository(application: Application) {
    private var medicineDoa: MedicineDao? = null
    private var allMedicine: LiveData<List<Medicine>>? = null

    init {
        val retailerDb = RetailerDb.getInstance(application)
        medicineDoa = retailerDb.medicineDao
        if (medicineDoa != null)
            allMedicine = medicineDoa!!.getAllMedicines()
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

    @JvmName("getAllMedicines1")
    fun getAllMedicines(): LiveData<List<Medicine>>?{
        return allMedicine
    }
}