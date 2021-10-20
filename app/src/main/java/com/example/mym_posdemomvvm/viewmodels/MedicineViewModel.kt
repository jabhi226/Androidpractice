package com.example.mym_posdemomvvm.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mym_posdemomvvm.models.Medicine
import com.example.mym_posdemomvvm.repository.RetailerDbRepository

class MedicineViewModel(application: Application) : AndroidViewModel(application) {
    var repository: RetailerDbRepository? = null
    var allMedicines: LiveData<List<Medicine>>? = null

    init {
        repository = RetailerDbRepository(application)
        allMedicines = repository?.getAllMedicines()
    }
}