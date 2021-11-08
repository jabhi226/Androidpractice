package com.example.mym_posdemomvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mym_posdemomvvm.databinding.ItemMedicineItemBinding
import com.example.mym_posdemomvvm.fragments.ShowAllMedicineFragment
import com.example.mym_posdemomvvm.models.Medicine

class MedicineListAdapter(private val type: String) : PagingDataAdapter<Medicine, MedicineListAdapter.MedicineViewHolder>(
    object : DiffUtil.ItemCallback<Medicine>() {
    override fun areItemsTheSame(oldItem: Medicine, newItem: Medicine): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Medicine, newItem: Medicine): Boolean {
        return oldItem.id == newItem.id
    }

}) {

    lateinit var binding: ItemMedicineItemBinding

    class MedicineViewHolder(private val item: ItemMedicineItemBinding) : RecyclerView.ViewHolder(item.root) {
        fun showAllMedicines(medicine: Medicine?) {
            this.item.apply {
                medicineName.text = medicine?.id.toString() + " | " + medicine?.name + " | " + medicine?.manufactureId
                isH1.text = medicine?.isH1.toString()
                divisor.text = medicine?.divisor.toString()
            }
        }

        fun showAllMedicinesStock(medicine: Medicine?) {
            this.item.apply {
                medicineName.text = medicine?.name + " | " + medicine?.manufactureId
                isH1.visibility = View.GONE
                divisor.text = medicine?.stock.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        binding = ItemMedicineItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MedicineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        when (type){
            ShowAllMedicineFragment.SHOW_ALL_MEDICINE -> {
                holder.showAllMedicines(getItem(position))
            }
            ShowAllMedicineFragment.SHOW_ALL_MEDICINE_STOCK -> {
                holder.showAllMedicinesStock(getItem(position))
            }
        }
    }
}