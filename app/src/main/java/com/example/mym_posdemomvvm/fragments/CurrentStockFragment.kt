package com.example.mym_posdemomvvm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mym_posdemomvvm.adapters.MedicineListAdapter
import com.example.mym_posdemomvvm.databinding.FragmentCurrentStockBinding

class CurrentStockFragment : Fragment() {

    private var mBinding: FragmentCurrentStockBinding? = null
    private val binding get() = mBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentCurrentStockBinding.inflate(inflater)
        return binding?.root
    }

    private var adapter: MedicineListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

}