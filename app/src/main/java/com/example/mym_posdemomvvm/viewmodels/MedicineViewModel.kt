package com.example.mym_posdemomvvm.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.mym_posdemomvvm.models.Manufacture
import com.example.mym_posdemomvvm.models.Medicine
import com.example.mym_posdemomvvm.models.Medicine1
import com.example.mym_posdemomvvm.repository.MPosRetailerDbRepository
import com.example.mym_posdemomvvm.utils.Utils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import java.util.jar.Attributes
import kotlin.coroutines.coroutineContext

class MedicineViewModel(application: Application) : AndroidViewModel(application){
    var repositoryMPos: MPosRetailerDbRepository = MPosRetailerDbRepository(application)

    private var allMedicines: LiveData<List<Medicine>>? = repositoryMPos.getAllMedicines()
    var allMedicinesFromPaging: Flow<PagingData<Medicine>>? = null
    var allMedicinesFromPagingOfRedBook: Flow<PagingData<Medicine1>>? = repositoryMPos.getAllMedicinesFromPagingOfRedBook()?.cachedIn(viewModelScope)
    var allMedicinesCountOfRedBook: MutableLiveData<Int> = MutableLiveData()
    var allMedicinesFromPagingOfRedBookLiveDat: MutableLiveData<PagingData<Medicine1>> = MutableLiveData()
    var allMedicinesContains: MutableLiveData<List<Medicine>> = MutableLiveData()
    var allMedicinesContainsOfRedBook: MutableLiveData<PagingData<Medicine1>> = MutableLiveData()
    var allManufactures: LiveData<List<Manufacture>>? = null

    fun updateAllMedicineContains(name: String){
        Log.d("SALE_LOG_UPDATE: ", name)
//        Utils.showToast(getApplication(), name)
//        val a = repositoryMPos.getAllMedicinesContains(name)?.value
//        allMedicinesContains?.value = a
//        allMedicinesContains?.value = repositoryMPos.getAllMedicines()?.value
        allMedicinesContains.value = repositoryMPos.getAllMedicinesContains(name)?.value
    }

    fun searchMedicineByNameOfRedBook(name: String){
//        val a = repositoryMPos.getAllMedicineContainsOfRedBook(name).cachedIn(viewModelScope)
//        Log.d("FLOW_UPDATE", "${a.toString()} | ${allMedicinesContainsOfRedBook.toString()}")
        allMedicinesContainsOfRedBook.value = repositoryMPos.getAllMedicineContainsOfRedBook(name).cachedIn(viewModelScope).value
    }

    fun updateAllMedicinesCountOfRedBook() {
        allMedicinesCountOfRedBook.value = repositoryMPos.getAllMedicinesCountOfRedBook()
    }

    init {
        allMedicinesFromPaging =
            repositoryMPos.getAllMedicinesFromPaging()?.cachedIn(viewModelScope)

//        allMedicinesFromPagingOfRedBookLiveDat.value = repositoryMPos.getAllMedicinesFromPagingOfRedBookLiveData()?.value

        allMedicinesContains.value = repositoryMPos.getAllMedicines()?.value
        allManufactures = repositoryMPos.getAllManufactures()
    }
}