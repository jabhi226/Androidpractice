package com.example.floatingkeyboard

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.floatingkeyboard.KeyboardAdapter.Companion.ITEM_KEY
import com.example.floatingkeyboard.KeyboardAdapter.Companion.ITEM_SPACE


class DragViewActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_view)
        var dX: Float = 0.0f
        var dY: Float = 0.0f
        findViewById<View>(R.id.view_drag).apply {
            val w = Resources.getSystem().displayMetrics.widthPixels
            val h = Resources.getSystem().displayMetrics.heightPixels
            this.setOnTouchListener { view, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        dX = view.x - event.rawX
                        dY = view.y - event.rawY
                    }

                    MotionEvent.ACTION_MOVE -> {
                        val ex = event.rawX + dX
                        val ey = event.rawY + dY
                        println("===> $ex | $ey | $w | $h | ${view.width + ex} | ${(w > view.width + ex )} | ${(ex > 0)} | ${(ey > 0)} | ${(h > view.height + ey)}")
                        if ((w > view.width + ex ) && (ex > 0) && (ey > 0) && (h > view.height + ey)) {
                            view.animate()
                                .x(event.rawX + dX)
                                .y(event.rawY + dY)
                                .setDuration(0)
                                .start()
                        }
                    }

                    else -> {
                        return@setOnTouchListener false
                    }
                }
                return@setOnTouchListener true
            }
        }

        initKyeBoard()
    }

    private fun initKyeBoard() {
        val adapter = KeyboardAdapter()
        findViewById<RecyclerView>(R.id.rv_keyboard)?.apply {
            this.adapter = adapter
            this.layoutManager = StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL)
        }
        adapter.submitList(getData())
    }

    private fun getData(): List<Key>{
        val list = arrayListOf<Key>()
        for (i in 0..50)  {
            if (i % 6 == 0){
                list.add(Key(i.toString(), ITEM_KEY))
            } else {
                list.add(Key(i.toString(), ITEM_SPACE))
            }
        }
        return list
    }
}