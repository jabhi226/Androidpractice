package com.example.mym_posdemomvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mym_posdemomvvm.databinding.ItemMedicineItemBinding
import com.example.mym_posdemomvvm.models.Medicine

class MedicineListAdapter : ListAdapter<Medicine, MedicineListAdapter.MedicineViewHolder>(
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
        fun bindData(medicine: Medicine) {
            this.item.apply {
                medicineName.text = medicine.name
                isH1.text = medicine.isH1.toString()
                divisor.text = medicine.divisor.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        binding = ItemMedicineItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MedicineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}