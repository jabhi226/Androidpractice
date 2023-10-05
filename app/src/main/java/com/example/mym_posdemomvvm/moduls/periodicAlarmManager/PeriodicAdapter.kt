package com.example.mym_posdemomvvm.moduls.periodicAlarmManager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mym_posdemomvvm.databinding.ItemMedicineListForSaleBinding

class PeriodicAdapter :
    ListAdapter<String, PeriodicAdapter.MyVH>(object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }) {

    inner class MyVH(val i: ItemMedicineListForSaleBinding) : RecyclerView.ViewHolder(i.root) {
        fun bindData(holder: String) {
            i.saleMedicine.text = holder
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        return MyVH(
            ItemMedicineListForSaleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        holder.bindData(getItem(position))
    }
}