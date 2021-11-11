package com.example.mym_posdemomvvm.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mym_posdemomvvm.R
import com.example.mym_posdemomvvm.databinding.FragmentAddMedicineBinding
import com.example.mym_posdemomvvm.models.Medicine
import com.example.mym_posdemomvvm.models.Medicine1
import com.example.mym_posdemomvvm.utils.Utils
import com.example.mym_posdemomvvm.viewmodels.MedicineViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AddMedicineFragment : Fragment(), View.OnClickListener {

    private var mBinding: FragmentAddMedicineBinding? = null
    private val binding get() = mBinding

    private var medicineViewModel: MedicineViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentAddMedicineBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initViewModel()
        initSpinner()
    }

    var manufactureAdapter: ArrayAdapter<String>? = null
    private val manufactureList = ArrayList<String>()
    private val manufactureIdList = ArrayList<Int>()
    var manufactureId = -1
    private fun initSpinner() {
        manufactureAdapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item,
            R.id.spinner_txt
        )
        manufactureAdapter?.setDropDownViewResource(R.layout.dropdown_view)
        binding?.manufacture?.adapter = manufactureAdapter

        binding?.manufacture?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                manufactureId = manufactureIdList[position]
                Log.d("ADD_MANUFACTURE", position.toString() + id.toString() +" | "+ manufactureIdList[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

    private fun initListener() {
        binding?.button?.setOnClickListener(this)
    }

    private fun initViewModel() {
        medicineViewModel = ViewModelProvider(this)[MedicineViewModel::class.java]
        medicineViewModel?.allManufactures?.observe(viewLifecycleOwner, {
            if (it != null){
                manufactureList.clear()
                it.forEach { manufacture ->
                    manufactureList.add(manufacture.manufacturerName)
                    manufactureIdList.add(manufacture.manufactureId)
                    Log.d("ADD_MANUFACTURE", " -> ${manufacture.manufactureId} | ${manufacture.manufacturerName}")
                }
                manufactureAdapter?.addAll(manufactureList)
                manufactureAdapter?.notifyDataSetChanged()
            }
        })
        lifecycleScope.launch {
            medicineViewModel?.allMedicinesFromPagingOfRedBook?.collectLatest {
                Utils.showToast(requireContext(), "Data Inserted!!")
                medicineViewModel?.updateAllMedicinesCountOfRedBook()
            }
        }
        medicineViewModel?.allMedicinesCountOfRedBook?.observe(viewLifecycleOwner, {
            binding?.button?.text = it.toString()
        })
//        medicineViewModel?.allMedicines?.observe(viewLifecycleOwner, {
//            it?.forEach { medicine ->
////                Utils.showToast(requireContext(), medicine.name)
//            }
//        })
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding?.button?.id -> {
//                medicineViewModel?.repositoryMPos?.insert(
//                    Medicine(
//                        binding?.nameEt?.text.toString(),
//                        binding?.isH1Et?.text.toString().toInt() != 0,
//                        binding?.divisorTv?.text.toString().toInt(),
//                        manufactureId = manufactureId
//                    )
//                )
                for (i in 1..1){
                    medicineViewModel?.repositoryMPos?.insert(
                        Medicine1(
                            java.util.UUID.randomUUID().toString(),
                            java.util.UUID.randomUUID().toString(),
                            binding?.nameEt?.text.toString(),
                            java.util.UUID.randomUUID().toString(),
                            binding?.isH1Et?.text.toString().toInt(),
                            java.util.UUID.randomUUID().toString(),
                            java.util.UUID.randomUUID().toString(),
                            "100",
                            java.util.UUID.randomUUID().toString(),
                            100,
                            java.util.UUID.randomUUID().toString(),
                            java.util.UUID.randomUUID().toString(),
                            binding?.divisorTv?.text.toString().toInt(),
                            100,
                            100,
                            100,
                            100,
                            java.util.UUID.randomUUID().toString(),
                            java.util.UUID.randomUUID().toString(),
                            java.util.UUID.randomUUID().toString(),
                            100,
                            java.util.UUID.randomUUID().toString(),
                            java.util.UUID.randomUUID().toString(),
                            java.util.UUID.randomUUID().toString(),
                            java.util.UUID.randomUUID().toString(),
                            java.util.UUID.randomUUID().toString(),
                            java.util.UUID.randomUUID().toString(),
                            java.util.UUID.randomUUID().toString(),
//                            java.util.UUID.randomUUID().toString(),
//                            100
                        )
                    )
                }
            }
        }
    }

}