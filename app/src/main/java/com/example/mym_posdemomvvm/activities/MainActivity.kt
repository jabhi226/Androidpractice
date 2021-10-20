package com.example.mym_posdemomvvm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mym_posdemomvvm.databinding.ActivityMainBinding
import com.example.mym_posdemomvvm.utils.Utils
import com.example.mym_posdemomvvm.viewmodels.MedicineViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var medicineViewModel: MedicineViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initListener()
        getMeds()
    }

    private fun initViewModel() {
        medicineViewModel = ViewModelProvider(this)[MedicineViewModel::class.java]
        medicineViewModel.allMedicines?.observe(this, Observer {
            it.forEach { medicine ->
                Log.e("MEDICINES", "getMeds: ${medicine.name}")
                Utils.showToast(this, medicine.name)
            }
        })
    }

    private fun getMeds() {
    }

    private fun initListener() {
        binding.sales.setOnClickListener(this)
        binding.purchase.setOnClickListener(this)
        binding.addMedicine.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.sales.id -> {

            }
            binding.purchase.id -> {

            }
            binding.addMedicine.id -> {

            }
            binding.showAllMedicines.id -> {

            }
        }
    }
}