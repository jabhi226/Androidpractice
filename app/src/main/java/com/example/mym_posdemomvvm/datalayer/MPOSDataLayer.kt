package com.example.mym_posdemomvvm.datalayer

import androidx.lifecycle.LiveData
import com.example.mym_posdemomvvm.moduls.mposPoc.data.db.daos.MedicineDao
import com.example.mym_posdemomvvm.moduls.mposPoc.data.models.Medicine

class MPOSDataLayer {

    private var medicineDoa: MedicineDao? = null
    private var medicineService: MPosServiceClient? = null

//    fun getAllMedicineData():LiveData<List<Medicine>>?{

//        if (hasDataInRoom()){
//            return medicineDoa?.getMedicinesContains("crocin")
//        }else{
//            return medicineService?.getMedicineFromApi()
//        }
//
//    }
}