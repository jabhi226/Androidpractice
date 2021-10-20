package com.example.mym_posdemomvvm.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mym_posdemomvvm.adapters.MedicineListAdapter
import com.example.mym_posdemomvvm.databinding.FragmentShowAllMedicineBinding
import com.example.mym_posdemomvvm.models.Medicine
import com.example.mym_posdemomvvm.utils.Utils
import com.example.mym_posdemomvvm.viewmodels.MedicineViewModel

class ShowAllMedicineFragment : Fragment() {

    private var mBinding: FragmentShowAllMedicineBinding? = null
    private val binding get() = mBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentShowAllMedicineBinding.inflate(inflater, container, false)
//        inflater.inflate(R.layout.fragment_show_all_medicine, container, false)
        return binding?.root
    }

    var adapter: MedicineListAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MedicineListAdapter()
        binding?.medicineListRv?.adapter = adapter
        binding?.medicineListRv?.layoutManager = LinearLayoutManager(requireContext())
        adapter?.submitList(tempList)
    }

    private var tempList = ArrayList<Medicine>()
    private lateinit var medicineViewModel: MedicineViewModel
    private fun initViewModel() {
        medicineViewModel = ViewModelProvider(this)[MedicineViewModel::class.java]
        medicineViewModel.allMedicines?.observe(this, Observer {
            tempList = it as ArrayList<Medicine>
            adapter?.submitList(it)
        })
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}