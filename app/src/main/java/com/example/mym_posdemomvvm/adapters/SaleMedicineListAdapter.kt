package com.example.mym_posdemomvvm.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mym_posdemomvvm.databinding.ItemMedicineListForSaleBinding
import com.example.mym_posdemomvvm.models.Medicine
import com.example.mym_posdemomvvm.models.Medicine1
import com.example.mym_posdemomvvm.utils.Utils

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
                    saleMedicine.text = medicine.id.toString() + " | " + medicine.name + " | " + medicine.stock.toString()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemMedicineListForSaleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}


class SaleMedicineListAdapterOfRedBook: ListAdapter<Medicine1, SaleMedicineListAdapterOfRedBook.ViewHolder>(
    object: DiffUtil.ItemCallback<Medicine1>(){
        override fun areItemsTheSame(oldItem: Medicine1, newItem: Medicine1): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Medicine1, newItem: Medicine1): Boolean {
            return oldItem.productUcode == newItem.productUcode
        }
    })
{
    class ViewHolder(private val item: ItemMedicineListForSaleBinding): RecyclerView.ViewHolder(item.root) {
        fun bindData(medicine: Medicine1?, itemCount: Int) {
            item.apply {
                if (medicine != null) {
                    saleMedicine.text = medicine.productUcode.toString() + " | " + medicine.productName + " | " + medicine.packformName.toString() + " | " + medicine.packeQuantityValue.toString() + " | " + medicine.manufacturer.toString() + " | " + medicine.manufacturerId.toString()
                } else {
                    Utils.showToast(item.root.context, "med empty")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemMedicineListForSaleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position), itemCount)
        Log.d("ITEMCOUNT", "$itemCount")

    }
}