package com.example.mym_posdemomvvm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mym_posdemomvvm.R
import com.example.mym_posdemomvvm.databinding.FragmentAddMedicineBinding
import com.example.mym_posdemomvvm.models.Medicine
import com.example.mym_posdemomvvm.viewmodels.MedicineViewModel

class AddMedicineFragment : Fragment(), View.OnClickListener {

    private var mBinding: FragmentAddMedicineBinding? = null
    private val binding get() = mBinding
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
    }

    private fun initListener() {
        binding?.button?.setOnClickListener(this)
    }

    private var medicineViewModel: MedicineViewModel? = null
    private fun initViewModel() {
        medicineViewModel = ViewModelProvider(this)[MedicineViewModel::class.java]
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding?.button?.id -> {
                medicineViewModel?.repository?.insert(
                    Medicine(
                        binding?.nameEt?.text.toString(),
                        binding?.isH1Et?.text.toString().toInt() != 0,
                        binding?.divisorTv?.text.toString().toInt())
                )
            }
        }
    }

}