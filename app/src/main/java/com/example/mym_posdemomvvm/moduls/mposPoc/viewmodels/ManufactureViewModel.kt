package com.example.mym_posdemomvvm.moduls.mposPoc.viewmodels

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.view.allViews
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mym_posdemomvvm.moduls.mposPoc.data.models.Manufacture
import com.example.mym_posdemomvvm.repository.MPosRetailerDbRepository

/**
 * ViewModel contains all data and business logic for activity and fragments,
 * so that activity and fragments is used to show only data on UI, nothing else.
 */
class ManufactureViewModel(application: Application): AndroidViewModel(application){
    var repositoryMPos: MPosRetailerDbRepository = MPosRetailerDbRepository(application)

    var allManufactures: LiveData<List<Manufacture>>? = repositoryMPos.getAllManufactures()
    var isUpdate: MutableLiveData<Boolean> = MutableLiveData(false)
    var isDataValid: MutableLiveData<Boolean> = MutableLiveData(false)
    var manufactureToUpdate: Manufacture? = null

}