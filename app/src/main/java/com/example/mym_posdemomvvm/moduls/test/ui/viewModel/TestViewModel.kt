package com.example.mym_posdemomvvm.moduls.test.ui.viewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mym_posdemomvvm.moduls.test.ui.fragments.FirstFragment
import kotlinx.coroutines.flow.MutableStateFlow

class TestViewModel : ViewModel() {

    val currentFragment: MutableStateFlow<Fragment> = MutableStateFlow(FirstFragment())
    val currentFragmentName: MutableStateFlow<String> = MutableStateFlow(FirstFragment().javaClass.simpleName)

//    init {
//        CoroutineScope(Dispatchers.Default).launch {
//            currentFragment.emit(FirstFragment())
//        }
//    }

    var progress: MutableLiveData<String> = MutableLiveData("25")

}