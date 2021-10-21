package com.example.mym_posdemomvvm.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mym_posdemomvvm.adapters.SaleMedicineListAdapter
import com.example.mym_posdemomvvm.databinding.FragmentSalesBinding
import com.example.mym_posdemomvvm.models.Medicine
import com.example.mym_posdemomvvm.viewmodels.MedicineViewModel

class SalesFragment : Fragment() {

    private var mBinding: FragmentSalesBinding? = null
    private val binding get() = mBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentSalesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTextWatcher()
        initViewModel()
    }

    private var medicineViewModel: MedicineViewModel? = null
    private var medicineListWithName = ArrayList<Medicine>()
    private fun initViewModel() {
        medicineViewModel = ViewModelProvider(this)[MedicineViewModel::class.java]
        medicineViewModel?.allMedicinesContains?.observe(viewLifecycleOwner, Observer {
            medicineListWithName = it as ArrayList<Medicine>
            adapter?.submitList(it)
        })
    }

    private fun initTextWatcher() {
        binding?.medicineNameEt?.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s !== null){
                    if (s.length > 4){
                        setMedicineListAdapter(s.toString())
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private var adapter: SaleMedicineListAdapter? = null
    private fun setMedicineListAdapter(name: String) {
        medicineViewModel?.repository?.medicineContains(name)
        if (adapter == null){
            adapter = SaleMedicineListAdapter()
            binding?.recyclerView?.adapter = adapter
            binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        }
        adapter?.submitList(medicineListWithName)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

}