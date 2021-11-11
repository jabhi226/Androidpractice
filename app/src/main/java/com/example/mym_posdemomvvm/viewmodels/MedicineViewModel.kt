package com.example.mym_posdemomvvm.viewmodels

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mym_posdemomvvm.models.Manufacture
import com.example.mym_posdemomvvm.models.Medicine
import com.example.mym_posdemomvvm.models.Medicine1
import com.example.mym_posdemomvvm.repository.MPosRetailerDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MedicineViewModel(application: Application) : AndroidViewModel(application){
    var repositoryMPos: MPosRetailerDbRepository = MPosRetailerDbRepository(application)

//    private var allMedicines: LiveData<List<Medicine>>? = repositoryMPos.getAllMedicines()
    var allMedicinesFromPaging: Flow<PagingData<Medicine>>? = null
    var allMedicinesFromPagingOfRedBook: Flow<PagingData<Medicine1>>? = repositoryMPos.getAllMedicinesFromPagingOfRedBook()?.cachedIn(viewModelScope)
    var allMedicinesCountOfRedBook: MutableLiveData<Int> = MutableLiveData()
    var allMedicinesFromPagingOfRedBookLiveDat: MutableLiveData<PagingData<Medicine1>> = MutableLiveData()
//    var allMedicinesContains: MutableLiveData<List<Medicine>> = MutableLiveData()
    var allMedicinesContainsOfRedBook: MutableLiveData<List<Medicine1>> = MutableLiveData()
    var allMedicinesContainsOfRedBookPaging: MutableLiveData<PagingData<Medicine1>> = MutableLiveData()
    var allManufactures: LiveData<List<Manufacture>>? = null

//    fun updateAllMedicineContains(name: String){
//        Log.d("SALE_LOG_UPDATE: ", name)
//        Utils.showToast(getApplication(), name)
//        val a = repositoryMPos.getAllMedicinesContains(name)?.value
//        allMedicinesContains?.value = a
//        allMedicinesContains?.value = repositoryMPos.getAllMedicines()?.value
//        allMedicinesContains.value = repositoryMPos.getAllMedicinesContains(name)?.value
//    }

    fun searchMedicineByNameOfRedBookPaging(name: String){
//        val a = repositoryMPos.getAllMedicineContainsOfRedBook(name).cachedIn(viewModelScope)
//        Log.d("FLOW_UPDATE", "${a.toString()} | ${allMedicinesContainsOfRedBook.toString()}")
        viewModelScope.launch {
            allMedicinesContainsOfRedBookPaging.value = repositoryMPos.getAllMedicineContainsOfRedBookPaging(name).cachedIn(viewModelScope).value
        }
    }

    fun searchMedicineByNameOfRedBook(name: String){
//        val a = repositoryMPos.getAllMedicineContainsOfRedBook(name).cachedIn(viewModelScope)
//        Log.d("FLOW_UPDATE", "${a.toString()} | ${allMedicinesContainsOfRedBook.toString()}")
        viewModelScope.launch {
            allMedicinesContainsOfRedBook.value = repositoryMPos.getAllMedicineContainsOfRedBook(name)
        }
    }

    fun updateAllMedicinesCountOfRedBook() {
        viewModelScope.launch {
            allMedicinesCountOfRedBook.value = repositoryMPos.getAllMedicinesCountOfRedBook()
        }
    }

    init {
        allMedicinesFromPaging = repositoryMPos.getAllMedicinesFromPaging()?.cachedIn(viewModelScope)
//        allMedicinesFromPagingOfRedBookLiveDat.value = repositoryMPos.getAllMedicinesFromPagingOfRedBookLiveData()?.value
//        allMedicinesContains.value = repositoryMPos.getAllMedicines()?.value
        allManufactures = repositoryMPos.getAllManufactures()
    }
}