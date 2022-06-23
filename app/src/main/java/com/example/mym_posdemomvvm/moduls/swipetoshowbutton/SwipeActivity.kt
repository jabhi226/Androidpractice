package com.example.mym_posdemomvvm.moduls.swipetoshowbutton

import SwipeHelper
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.*
import com.example.mym_posdemomvvm.R

class SwipeActivity : AppCompatActivity() {
    private val adapter = MyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe)

        val r: RecyclerView = findViewById(R.id.recyclerView)
        r.layoutManager = LinearLayoutManager(this)
        r.adapter = adapter
        adapter.submitList(getDataModelList())

        initSwipeHelper(r)
    }

    private fun initSwipeHelper(recyclerView: RecyclerView) {
        val itemTouchHelper = ItemTouchHelper(object : SwipeHelper(recyclerView) {
            override fun instantiateUnderlayButton(position: Int): List<UnderlayButton> {
                val buttons: List<UnderlayButton>
                val deleteButton = deleteButton(position)
                val markAsUnreadButton = markAsUnreadButton(position)
                val archiveButton = archiveButton(position)
                buttons = listOf(deleteButton, markAsUnreadButton, archiveButton)
                return buttons
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun toast(text: String, position: Int) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        adapter.notifyItemChanged(position)
    }

    private fun drawableToBitmap(drawable: Drawable?): Bitmap? {

        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        if (drawable == null)
            return null
        val bitmap =
            Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(10, 10, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    private fun deleteButton(position: Int): SwipeHelper.UnderlayButton {
        val a = drawableToBitmap(ContextCompat.getDrawable(this, R.drawable.ic_phonepe))

        return SwipeHelper.UnderlayButton(
            this,
            "",
            14.0f,
            R.color.grey_b3b0a3,
            object : SwipeHelper.UnderlayButtonClickListener {
                override fun onClick() {
                    toast("$position phonepay", position)
                }
            },
            a!!
        )
    }

    private fun getDataModelList(): ArrayList<DataModel> {
        val list = ArrayList<DataModel>()
        for (i in 0 until 30) {
            list.add(DataModel(i))
        }
        return list
    }

    private fun markAsUnreadButton(position: Int): SwipeHelper.UnderlayButton {
        val a = drawableToBitmap(ContextCompat.getDrawable(this, R.drawable.ic_whatsapp))

        return SwipeHelper.UnderlayButton(
            this,
            "",
            14.0f,
            R.color.grey_b3b0a3,
            object : SwipeHelper.UnderlayButtonClickListener {
                override fun onClick() {
                    toast("$position whatsapp", position)
                }
            },
            a!!
        )
    }

    private fun archiveButton(position: Int): SwipeHelper.UnderlayButton {
        val a = drawableToBitmap(ContextCompat.getDrawable(this, R.drawable.ic_google_pay_new))

        return SwipeHelper.UnderlayButton(
            this,
            "",
            14.0f,
            R.color.grey_b3b0a3,
            object : SwipeHelper.UnderlayButtonClickListener {
                override fun onClick() {
                    toast("$position gpay", position)
                }
            },
            a!!
        )
    }
}

class DataModel(
    val id: Int
)

class MyAdapter : ListAdapter<DataModel, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<DataModel>() {
        override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
            return oldItem.id == newItem.id
        }

    }
) {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txt: TextView = view.findViewById(R.id.medicine_name)
        fun bindData(item: DataModel) {
//            txt.setText(item.id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_medicine_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).bindData(getItem(position))
    }

}