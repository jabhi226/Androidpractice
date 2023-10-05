package com.example.mym_posdemomvvm.moduls.test.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestViewModel : ViewModel() {

    val fragment1Count = MutableLiveData<Int>()
    var progress: MutableLiveData<Int> = MutableLiveData()

    init {
        viewModelScope.launch {
            for (i in 0..Int.MAX_VALUE) {
                delay(1000)
                fragment1Count.postValue(i)
            }
        }
        viewModelScope.launch {
            for (i in 0 .. 100 step 5){
                delay(1000)
                progress.postValue(i)
                println("====> $i ${Thread.currentThread().name}")
            }
        }
    }

}