package com.example.mym_posdemomvvm.moduls.random

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.iterator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mym_posdemomvvm.R
import com.example.mym_posdemomvvm.databinding.ItemRandomBoxBinding

class RandomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)
        var isVisible = false
        val view = findViewById<View>(R.id.view_what)
        val rv = findViewById<RecyclerView>(R.id.rv_what)
        val tv = findViewById<TextView>(R.id.tv_what)
        rv.apply {
            val a = Adapter {
                isVisible = !isVisible
                view.visibility = if (isVisible) {
                    rv.setBackgroundColor(
                        ContextCompat.getColor(
                            this@RandomActivity,
                            R.color.transparent_black
                        )
                    )
                    tv.setTextColor(
                        ContextCompat.getColor(this@RandomActivity, R.color.white)
                    )
                    View.VISIBLE
                } else {
                    rv.setBackgroundColor(
                        ContextCompat.getColor(
                            this@RandomActivity,
                            R.color.transparent
                        )
                    )
                    tv.setTextColor(
                        ContextCompat.getColor(this@RandomActivity, R.color.black)
                    )
                    View.GONE
                }
            }
            adapter = a
            a.submitList(
                arrayListOf(
                    1,
                    2,
                    32,
                    32,
                    312,
                    31,
                    313,
                    123,
                    123,
                    123,
                    12312,
                    432,
                    432,
                    234234,
                    24
                )
            )
        }
    }

    class Adapter(val onClick: () -> Unit) :
        ListAdapter<Int, Adapter.VH>(object : DiffUtil.ItemCallback<Int>() {
            override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
                return oldItem == newItem
            }

        }) {
        inner class VH(val view: ItemRandomBoxBinding) : RecyclerView.ViewHolder(view.root) {
            init {
                view.root.setOnClickListener {
                    onClick()
                }
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            return VH(
                ItemRandomBoxBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
        }
    }
}