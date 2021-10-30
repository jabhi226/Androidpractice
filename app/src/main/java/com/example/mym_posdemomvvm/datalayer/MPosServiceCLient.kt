package com.example.mym_posdemomvvm.datalayer

import androidx.lifecycle.LiveData
import com.example.mym_posdemomvvm.models.Medicine

interface MPosServiceClient {

    fun getMedicineFromApi() : LiveData<List<Medicine>>?
}