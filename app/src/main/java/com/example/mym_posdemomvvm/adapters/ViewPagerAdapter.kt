package com.example.mym_posdemomvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mym_posdemomvvm.R

class ViewPagerAdapter(private val listOfImages: List<Int>) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return listOfImages.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_status_screen, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.img.setImageResource(listOfImages[position])
        holder.txt.text = listOfImages[position].toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.status_image)
        val txt = itemView.findViewById<TextView>(R.id.fake_status)
    }

}