package com.example.mym_posdemomvvm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mym_posdemomvvm.databinding.ActivityMainBinding
import com.example.mym_posdemomvvm.fragments.AddMedicineFragment
import com.example.mym_posdemomvvm.fragments.SalesFragment
import com.example.mym_posdemomvvm.fragments.ShowAllMedicineFragment
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
            }
        })
    }

    private fun getMeds() {
    }

    private fun initListener() {
        binding.sales.setOnClickListener(this)
        binding.purchase.setOnClickListener(this)
        binding.addMedicine.setOnClickListener(this)
        binding.showAllMedicines.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.sales.id -> {
                val fm = supportFragmentManager.beginTransaction()
                fm.replace(binding.mainFrame.id, SalesFragment(), "SalesFragment")
                fm.commit()
            }
            binding.purchase.id -> {

            }
            binding.addMedicine.id -> {
                val fm = supportFragmentManager.beginTransaction()
                fm.replace(binding.mainFrame.id, AddMedicineFragment(), "AddMedicineFragment")
                fm.commit()
            }
            binding.showAllMedicines.id -> {
                val fm = supportFragmentManager.beginTransaction()
                fm.replace(binding.mainFrame.id, ShowAllMedicineFragment(), "ShowAllMedicineFragment")
                fm.commit()
            }
        }
    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(binding.mainFrame.id)
        if (frag != null){
            frag.let {
                when (it.javaClass.simpleName) {
                    "ShowAllMedicineFragment" -> {
                        val f = supportFragmentManager.findFragmentByTag("ShowAllMedicineFragment")
                        if (f != null) {
                            supportFragmentManager.beginTransaction().remove(f).commit()
                        }
                    }
                    "SalesFragment" -> {
                        val f = supportFragmentManager.findFragmentByTag("SalesFragment")
                        if (f != null) {
                            supportFragmentManager.beginTransaction().remove(f).commit()
                        }
                    }
                    "AddMedicineFragment" -> {
                        val f = supportFragmentManager.findFragmentByTag("AddMedicineFragment")
                        if (f != null) {
                            supportFragmentManager.beginTransaction().remove(f).commit()
                        }
                    }
                    else -> {
                        super.onBackPressed()
                    }
                }
            }
        } else {
            super.onBackPressed()
        }
    }
}