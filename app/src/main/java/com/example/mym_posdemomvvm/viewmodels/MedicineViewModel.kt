package com.example.mym_posdemomvvm.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mym_posdemomvvm.models.Manufacture
import com.example.mym_posdemomvvm.models.Medicine
import com.example.mym_posdemomvvm.repository.MPosRetailerDbRepository
import com.example.mym_posdemomvvm.utils.Utils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import java.util.jar.Attributes

class MedicineViewModel(application: Application) : AndroidViewModel(application){
    var repositoryMPos: MPosRetailerDbRepository = MPosRetailerDbRepository(application)

    var allMedicines: LiveData<List<Medicine>>? = null
    var allMedicinesFromPaging: Flow<PagingData<Medicine>>? = null
    var allMedicinesContains: MutableLiveData<List<Medicine>?>? = MutableLiveData()
    var allManufactures: LiveData<List<Manufacture>>? = null

    fun updateAllMedicineContains(name: String){
        Log.d("SALE_LOG_UPDATE: ", name)
//        Utils.showToast(getApplication(), name)
//        val a = repositoryMPos.getAllMedicinesContains(name)?.value
//        allMedicinesContains?.value = a
//        allMedicinesContains?.value = repositoryMPos.getAllMedicines()?.value
        allMedicinesContains!!.value = repositoryMPos.getAllMedicinesContains(name)?.value
    }

    init {
        allMedicines = repositoryMPos.getAllMedicines()
        allMedicinesFromPaging =
            repositoryMPos.getAllMedicinesFromPaging()?.cachedIn(viewModelScope)
        allMedicinesContains?.value = repositoryMPos.getAllMedicines()?.value
        allManufactures = repositoryMPos.getAllManufactures()
    }
}