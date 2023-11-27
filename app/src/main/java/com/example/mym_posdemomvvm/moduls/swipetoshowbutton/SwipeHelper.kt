import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.mym_posdemomvvm.R
import java.util.*
import kotlin.math.abs
import kotlin.math.max


abstract class SwipeHelper(
    private val recyclerView: RecyclerView
) : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.ACTION_STATE_IDLE,
    ItemTouchHelper.LEFT
) {
    private var swipedPosition = -1
    private val buttonsBuffer: MutableMap<Int, List<UnderlayButton>> = mutableMapOf()
    private val recoverQueue = object : LinkedList<Int>() {
        override fun add(element: Int): Boolean {
            if (contains(element)) return false
            return super.add(element)
        }
    }

    var previousRawX: Float = -1F
    @SuppressLint("ClickableViewAccessibility")
    private val touchListener = View.OnTouchListener { _, event ->
        if (swipedPosition < 0) return@OnTouchListener false
        recoverQueue.add(swipedPosition)
        buttonsBuffer[swipedPosition]?.forEach { it.handle(event = event) }
        swipedPosition = -1


        if (previousRawX > event.rawX){
            return@OnTouchListener false
        }
        previousRawX = event.rawX
        println("touchListener -> ${event.action} | ${event.rawX} | ${event.rawY}")

        recoverSwipedItem()
        return@OnTouchListener true
    }

    init {
        recyclerView.setOnTouchListener(touchListener)
    }

    private fun recoverSwipedItem() {
        while (!recoverQueue.isEmpty()) {
            val position = recoverQueue.poll() ?: return
            recyclerView.adapter?.notifyItemChanged(position)
        }
    }

    private fun drawButtons(
        canvas: Canvas,
        buttons: List<UnderlayButton>,
        itemView: View,
        dX: Float
    ) {
        var right = itemView.right
        buttons.forEachIndexed { index, button ->
            val width = button.intrinsicWidth / buttons.intrinsicWidth() * abs(dX)
            val left = right - width
            button.draw(
                canvas,
                RectF(left,
                    itemView.top.toFloat(),
                    right.toFloat(),
                    itemView.bottom.toFloat()
                ),
                itemView,
                index
            )
            right = left.toInt()
        }
    }

    override fun onChildDraw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {

        val position = viewHolder.adapterPosition
        var maxDX = dX
        val itemView = viewHolder.itemView

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            if (dX < 0) { // swipe left
                if (!buttonsBuffer.containsKey(position)) {
                    buttonsBuffer[position] = instantiateUnderlayButton(position)
                }
                val buttons = buttonsBuffer[position] ?: return
                if (buttons.isEmpty()) return
                maxDX = max(-buttons.intrinsicWidth(), dX)
                drawButtons(canvas, buttons, itemView, maxDX)
            }
        }

        super.onChildDraw(
            canvas,
            recyclerView,
            viewHolder,
            maxDX,
            dY,
            actionState,
            isCurrentlyActive
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        println("onSwiped -> $direction")
        val position = viewHolder.adapterPosition
        if (swipedPosition != position) {
            recoverQueue.add(swipedPosition)
            swipedPosition = position
            recoverSwipedItem()
        }
    }

    abstract fun instantiateUnderlayButton(position: Int): List<UnderlayButton>

    //region UnderlayButton
    interface UnderlayButtonClickListener {
        fun onClick()
    }

    class UnderlayButton(
        private val context: Context,
        private val title: String,
        textSize: Float,
        @ColorRes private val colorRes: Int,
        private val clickListener: UnderlayButtonClickListener,
        private val img: Bitmap
    ) {
        private var clickableRegion: RectF? = null
        private val textSizeInPixel: Float = textSize * context.resources.displayMetrics.density // dp to px
        private val horizontalPadding = 100.0f
        val intrinsicWidth: Float

        init {
            val paint = Paint()
            paint.textSize = textSizeInPixel
            paint.typeface = Typeface.DEFAULT_BOLD
            paint.textAlign = Paint.Align.LEFT
            val titleBounds = Rect()
            paint.getTextBounds(title, 0, title.length, titleBounds)
            intrinsicWidth = titleBounds.width() + 2 * horizontalPadding
        }

        fun draw(canvas: Canvas, rect: RectF, itemView: View, index: Int) {

//            rect.set(rect.left, rect.top + 80, rect.right, rect.bottom - 80)
            println("$index ---> ${rect.height()} : ${rect.width()} : ${rect.bottom} : ${rect.top}: ${rect.left} : ${rect.right}")

//            Draw background
            val paint = Paint()
//            paint.style = Paint.Style.FILL
//            paint.color = ContextCompat.getColor(context, R.color.white)
//            paint.strokeWidth = 5F
//            paint.alpha = 80
//            canvas.drawRect(rect, paint)

//            line
            val p = Paint()
            p.color = ContextCompat.getColor(context, R.color.grey_b3b0a3)
            p.strokeWidth = 5F
            p.style = Paint.Style.FILL_AND_STROKE
            canvas.drawLine(rect.left - 8, rect.top + 16, rect.left - 8, rect.bottom - 16, p)

//            image
//            canvas.drawBitmap(img,
//                ((rect.left + rect.right) / 2) - (rect.width() / 2) + (img.width / 2),
//                ((rect.top + rect.bottom) / 2) - (rect.height() / 2) + (img.height.toFloat()),
//                paint)
            canvas.drawBitmap(img,
                rect.centerX() - img.width / 2,
                rect.centerY() - img.height / 2,
                paint)


            //
//            val icon = ContextCompat.getDrawable(context, R.drawable.ic_whatsapp)
//            val iconSize = icon?.intrinsicHeight ?: 1
//            val halfIcon: Int = iconSize / 2
//            val top: Int =
//                itemView.top + ((itemView.bottom - itemView.top) / 2 - halfIcon)
//            icon?.setBounds(
//                itemView.left + 0 + 0,
//                top,
//                itemView.left + 0 + icon.intrinsicWidth,
//                top + icon.intrinsicHeight
//            )
//            if (swipeRightActionIconTint != null) icon.setColorFilter(
//                swipeRightActionIconTint,
//                PorterDuff.Mode.SRC_IN
//            )
//            icon?.draw(canvas)
            clickableRegion = rect
        }

        fun handle(event: MotionEvent) {
            clickableRegion?.let {
                if (it.contains(event.x, event.y)) {
                    clickListener.onClick()
                }
            }
        }
    }
    //endregion
}

private fun List<SwipeHelper.UnderlayButton>.intrinsicWidth(): Float {
    if (isEmpty()) return 0.0f
    return map { it.intrinsicWidth }.reduce { acc, fl -> acc + fl }
}