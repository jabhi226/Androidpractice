package com.example.mym_posdemomvvm.moduls.spannablegridlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mym_posdemomvvm.R
import com.example.mym_posdemomvvm.databinding.ItemBoxBinding

class StaggeredGridActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staggered_grid)
        val a = MyAdapter()
        findViewById<RecyclerView>(R.id.rv_staggred).apply {
            adapter = a
            val gaggeredGridLayoutManager =
                StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL)
            gaggeredGridLayoutManager.gapStrategy =
                StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            layoutManager = gaggeredGridLayoutManager
            addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    gaggeredGridLayoutManager.invalidateSpanAssignments()
                }
            })
        }
        a.submitList(getList())
    }

    private fun getList(): MutableList<Item>? {
        val l = arrayListOf<Item>()
        for (i in 0 .. 40) {
            if (i == 15 || i == 18 || i == 13){
                l.add(Item(Int.MAX_VALUE))
                continue
            }
            l.add(Item(i))
        }
        return l
    }
}


data class Item(val num: Int)
class MyAdapter() :
    ListAdapter<Item, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.num == newItem.num
        }

    }) {

    inner class CommonVh(private val binding: ItemBoxBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: Item?) {
            binding.tvText.text = item?.num?.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CommonVh(ItemBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CommonVh).bindData(getItem(position))
    }
}