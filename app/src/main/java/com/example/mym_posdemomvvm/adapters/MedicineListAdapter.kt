package com.example.mym_posdemomvvm.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mym_posdemomvvm.databinding.ItemMedicineItemBinding
import com.example.mym_posdemomvvm.fragments.ShowAllMedicineFragment
import com.example.mym_posdemomvvm.models.Medicine
import com.example.mym_posdemomvvm.models.Medicine1

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


class MedicineListAdapterOfRedBook(private val type: String) : PagingDataAdapter<Medicine1, MedicineListAdapterOfRedBook.TempHolder>(
    object: DiffUtil.ItemCallback<Medicine1>(){
        override fun areItemsTheSame(oldItem: Medicine1, newItem: Medicine1): Boolean {
            return oldItem.productUcode == newItem.productUcode
        }

        override fun areContentsTheSame(oldItem: Medicine1, newItem: Medicine1): Boolean {
            return oldItem == newItem
        }

    }
) {
    private lateinit var binding1: ItemMedicineItemBinding

    class TempHolder(private val viewItem: ItemMedicineItemBinding) : RecyclerView.ViewHolder(viewItem.root) {
        fun bind(medicine: Medicine1?, p: Int) {
            if (medicine != null)
                viewItem.medicineName.text = medicine.productUcode.toString() + " | " + medicine.productName + " | " + medicine.packformName.toString() + " | " + medicine.packeQuantityValue.toString() + " | " + medicine.manufacturer.toString() + " | " + medicine.manufacturerId.toString()

//            viewItem.medicineName.text = item?.productName
//            viewItem.divisor.text = item?.productName.toString() + "  |  " + p.toString()
//            viewItem.isH1.text = item?.composition
            medicine.let {
                if (it != null){
                    Log.d("BULK_TESTING_DATA: ", "${it.productUcode} | ${it.productType} | ${it.productName} | ${it.composition} | ${it.schedule} | ${it.productHsnCode}")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempHolder {
        binding1 = ItemMedicineItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TempHolder(binding1)
    }

    override fun onBindViewHolder(holder: TempHolder, position: Int) {
        val p = position
        holder.bind(getItem(p), p)
    }
}