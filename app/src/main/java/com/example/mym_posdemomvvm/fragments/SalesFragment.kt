package com.example.mym_posdemomvvm.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mym_posdemomvvm.adapters.SaleMedicineListAdapter
import com.example.mym_posdemomvvm.databinding.FragmentSalesBinding
import com.example.mym_posdemomvvm.utils.Utils
import com.example.mym_posdemomvvm.viewmodels.ManufactureViewModel
import com.example.mym_posdemomvvm.viewmodels.MedicineViewModel

class SalesFragment : Fragment(), View.OnClickListener {

    private var mBinding: FragmentSalesBinding? = null
    private val binding get() = mBinding

    private var medicineViewModel: MedicineViewModel? = null

    private var adapter: SaleMedicineListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentSalesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setRecycler()
        initTextWatcher()
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

    private fun setRecycler() {
        if (adapter == null) {
            adapter = SaleMedicineListAdapter()
            binding?.recyclerView?.adapter = adapter
            binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext(),)
        }
    }

    private fun initViewModel() {
        medicineViewModel = ViewModelProvider(this)[MedicineViewModel::class.java]
        medicineViewModel?.allMedicinesContains?.observe(viewLifecycleOwner, {
            if (it != null){
                Log.d("SALE_LOG_UPDATE ", "" + it.size)
                Utils.showToast(requireContext(), "List Size ${it.size}")
                adapter?.submitList(it)
            }
            else {
                Utils.showToast(requireContext(), "Empty List")
            }
        })

//        medicineViewModel?.repositoryMPos?.allMedicineContains?.observe(viewLifecycleOwner, {
//            if (it != null){
//                Log.d("SALE_LOG_UPDATE ", "" + it.size)
//                adapter?.submitList(it)
//            }
//        })
    }

    private fun initTextWatcher() {
        binding?.medicineNameEt?.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s !== null){
                    if (s.length > 2){
                        binding?.recyclerView?.visibility = View.VISIBLE
//                        medicineViewModel?.repositoryMPos?.getAllMedicinesContains(s.toString().lowercase())
                        medicineViewModel!!.updateAllMedicineContains(s.toString().lowercase())
                    } else {
                        binding?.recyclerView?.visibility = View.GONE
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    override fun onClick(v: View?) {

    }

}