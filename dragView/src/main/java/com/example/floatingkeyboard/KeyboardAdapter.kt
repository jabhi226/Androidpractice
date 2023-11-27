package com.example.floatingkeyboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.floatingkeyboard.databinding.ItemKeyBinding
import com.example.floatingkeyboard.databinding.ItemSpaceBinding

class KeyboardAdapter() :
    ListAdapter<Key, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Key>() {
        override fun areItemsTheSame(oldItem: Key, newItem: Key): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Key, newItem: Key): Boolean {
            return oldItem.key == newItem.key
        }
    }) {

    inner class CommonVh(private val binding: ItemKeyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: Key?) {
            binding.tvText.text = item?.key
        }
    }

    inner class SpaceVh(private val binding: ItemSpaceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: Key?) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_KEY -> {
                CommonVh(ItemKeyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            ITEM_SPACE -> {
                SpaceVh(ItemSpaceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            else -> {
                CommonVh(ItemKeyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    companion object {
        const val ITEM_KEY = 101
        const val ITEM_SPACE = 102
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            when (it.type) {
                ITEM_KEY -> {
                    (holder as CommonVh).bindData(getItem(position))
                }
                ITEM_SPACE -> {
                    (holder as SpaceVh).bindData(getItem(position))
                }
            }
        }
    }
}
