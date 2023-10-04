package com.example.mym_posdemomvvm.datalayer

import androidx.lifecycle.LiveData
import com.example.mym_posdemomvvm.moduls.mposPoc.data.models.Medicine

interface MPosServiceClient {

    fun getMedicineFromApi() : LiveData<List<Medicine>>?
}