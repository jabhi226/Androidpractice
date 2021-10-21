package com.example.mym_posdemomvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mym_posdemomvvm.databinding.ItemMedicineListForSaleBinding
import com.example.mym_posdemomvvm.models.Medicine

class SaleMedicineListAdapter: ListAdapter<Medicine, SaleMedicineListAdapter.ViewHolder>(
    object: DiffUtil.ItemCallback<Medicine>(){
        override fun areItemsTheSame(oldItem: Medicine, newItem: Medicine): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Medicine, newItem: Medicine): Boolean {
            return oldItem.id == newItem.id
        }
    })
{
    class ViewHolder(private val item: ItemMedicineListForSaleBinding): RecyclerView.ViewHolder(item.root) {
        fun bindData(medicine: Medicine?) {
            item.apply {
                if (medicine != null) {
                    saleMedicine.text = medicine.id.toString() + " | " + medicine.name
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemMedicineListForSaleBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}