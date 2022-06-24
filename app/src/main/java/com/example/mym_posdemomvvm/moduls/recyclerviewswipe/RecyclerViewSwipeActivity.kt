package com.example.mym_posdemomvvm.moduls.recyclerviewswipe

import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import com.example.mym_posdemomvvm.R
import com.example.mym_posdemomvvm.moduls.swipetoshowbutton.SwipeActivity
import com.example.mym_posdemomvvm.utils.Utils


class RecyclerViewSwipeActivity : AppCompatActivity() {

    private val adapter = MyAdapter(getDataModelList(), object : OnAdapterClickEvent {
        override fun onClick() {
            Utils.showToast(this@RecyclerViewSwipeActivity, "fldsjflsjflsf")
            hideCurrentItem(currentPosition)
        }
    })

    var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_swipe)

        val r: RecyclerView = findViewById(R.id.recyclerView)
        r.layoutManager = LinearLayoutManager(this)
        r.adapter = adapter
        adapter.notifyDataSetChanged()

        val itemTouchHelper = ItemTouchHelper(getItemTouchHelperSimpleCallback())
        itemTouchHelper.attachToRecyclerView(r)

        findViewById<TextView>(R.id.second_activity).setOnClickListener {
            val i = Intent(this, SwipeActivity::class.java)
            startActivity(i)
        }
    }

    private fun getItemTouchHelperSimpleCallback(): ItemTouchHelper.Callback {
        return object : ItemTouchHelper.Callback() {

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                if (viewHolder is MyAdapter.MyViewHolder) {
                    val foregroundView: View? = viewHolder.forground
                    getDefaultUIUtil().onSelected(foregroundView)
                }
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                val foregroundView: View? =
                    (viewHolder as MyAdapter.MyViewHolder).forground
                getDefaultUIUtil().clearView(foregroundView)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val v = (viewHolder as MyAdapter.MyViewHolder).forground
                getDefaultUIUtil().onDraw(
                    c,
                    recyclerView,
                    v,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }

            override fun onChildDrawOver(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder?,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val v = (viewHolder as MyAdapter.MyViewHolder).forground
                getDefaultUIUtil().onDrawOver(
                    c,
                    recyclerView,
                    v,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeMovementFlags(
                    ItemTouchHelper.DOWN or ItemTouchHelper.UP,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                )
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                println("onMove")
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                currentPosition = viewHolder.bindingAdapterPosition
                println("onSwiped --> $direction | $currentPosition")
                val currentList = getDataModelList()
                currentList[currentPosition].id = 102
                MyAdapter.updateCurrentList(currentList, adapter)
                adapter.notifyItemChanged(currentPosition)
            }

        }
    }

    fun hideCurrentItem(position: Int){
        Utils.showToast(this, "$position")
//        adapter.notifyItemChanged(position)
    }

    interface OnAdapterClickEvent {
        fun onClick()
    }

    private fun getDataModelList(): ArrayList<DataModel> {
        val list = ArrayList<DataModel>()
        for (i in 0 until 30) {
            list.add(DataModel(101))
        }
        return list
    }
}

class DataModel(
    var id: Int
)

class MyAdapter(
    val dataList: ArrayList<DataModel>,
    private val onClickEvent: RecyclerViewSwipeActivity.OnAdapterClickEvent
) :
//    ListAdapter<DataModel, RecyclerView.ViewHolder>(
//        object : DiffUtil.ItemCallback<DataModel>() {
//            override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
//                return oldItem == newItem
//            }
//
//            override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
//                return oldItem.id == newItem.id
//            }
//
//        }
//    )
    RecyclerView.Adapter<MyAdapter.MyViewHolder>()
{
    var currentDataList = dataList

    companion object {
        const val SHOW = 101
        const val HIDE = 102

        fun updateCurrentList(dataList: ArrayList<DataModel>, adapter: MyAdapter){
            adapter.updateCurrentList(dataList)
        }
    }

    private fun updateCurrentList(dataList: java.util.ArrayList<DataModel>) {
        currentDataList = dataList
    }

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val forground = view.findViewById<TextView>(R.id.foreground)
        val background = view.findViewById<LinearLayout>(R.id.background)
        val one = view.findViewById<TextView>(R.id.one)

        fun bindData(onClickEvent: RecyclerViewSwipeActivity.OnAdapterClickEvent) {
            one.setOnClickListener {
                onClickEvent.onClick()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return currentDataList[position].id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.hidden_recyclerview_item, parent, false)

        println("id --> $viewType")
        if (viewType == SHOW){
        } else {
            v.findViewById<TextView>(R.id.foreground).visibility = View.GONE
        }
        return MyViewHolder(v)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        (holder as MyViewHolder).bindData(onClickEvent)
    }

    override fun getItemCount(): Int {
        return currentDataList.size
    }

}