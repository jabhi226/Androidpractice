package com.example.mym_posdemomvvm.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mym_posdemomvvm.adapters.MedicineListAdapter
import com.example.mym_posdemomvvm.adapters.MedicineListAdapterOfRedBook
import com.example.mym_posdemomvvm.databinding.FragmentShowAllMedicineBinding
import com.example.mym_posdemomvvm.models.Medicine
import com.example.mym_posdemomvvm.utils.Utils
import com.example.mym_posdemomvvm.viewmodels.MedicineViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import kotlin.coroutines.suspendCoroutine

class ShowAllMedicineFragment(private val type: String) : Fragment() {

    private var mBinding: FragmentShowAllMedicineBinding? = null
    private val binding get() = mBinding

    companion object {
        const val SHOW_ALL_MEDICINE = "SHOW_ALL_MEDICINE"
        const val SHOW_ALL_MEDICINE_STOCK = "SHOW_ALL_MEDICINE_STOCK"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentShowAllMedicineBinding.inflate(inflater, container, false)
        return binding?.root
    }

//    private var adapter: MedicineListAdapter? = null
    var tempAdapter: MedicineListAdapterOfRedBook? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        adapter = MedicineListAdapter(type)
        tempAdapter = MedicineListAdapterOfRedBook(type)
        binding?.medicineListRv?.adapter = tempAdapter
        binding?.medicineListRv?.layoutManager = LinearLayoutManager(requireContext())
//        adapter?.submitList(allMedicinals)
        initViewModel()
    }

    private var allMedicinals  = ArrayList<Medicine>()
    private var allMedicinePagingData: PagingData<Medicine>? = null
    private lateinit var medicineViewModel: MedicineViewModel
    private fun initViewModel(){
        medicineViewModel = ViewModelProvider(this@ShowAllMedicineFragment)[MedicineViewModel::class.java]
//        medicineViewModel.allMedicines?.observe(viewLifecycleOwner, {
//            allMedicinals = it as ArrayList<Medicine>
//            Utils.showToast(requireContext(), allMedicinals.size.toString())
//            adapter?.submitList(it)
//        })

//        lifecycleScope.launch {
//            medicineViewModel.allMedicinesFromPaging?.collectLatest {
//                allMedicinePagingData = it
//                if (allMedicinePagingData != null){
////                    adapter?.submitData(allMedicinePagingData!!)
////                    Utils.showToast(requireContext(), allMedicinePagingData!!.toString() )
//                }
//            }
        lifecycleScope.launch {
            medicineViewModel.allMedicinesFromPagingOfRedBook?.collectLatest {
                tempAdapter?.submitData(it)
            }
        }
//        medicineViewModel.allMedicinesFromPagingOfRedBookLiveDat.observe(viewLifecycleOwner, {
//            lifecycleScope.launch {
//                if (it != null){
//                    tempAdapter?.submitData(it)
//                }
//            }
//        })
//        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}