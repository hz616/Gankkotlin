package com.hz.gank_kotlin.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hz.gank_kotlin.R
import com.hz.gank_kotlin.data.ui.GankItem

class MyDividerItemDecoration constructor(
    context: Context, callback: DecorationCallback
) : RecyclerView.ItemDecoration() {

    private var dividerHeight: Int = 0
    private var dividerPaddingLeft: Int = 0
    private var dividerPaddingRight: Int = 0
    private var paint: Paint
    private var mContext: Context = context
    private var mDecorationCallback = callback


    init {
        dividerHeight = dp2px(20)
        dividerPaddingLeft = dp2px(10)
        dividerPaddingRight = dp2px(10)

        paint = Paint(Paint.ANTI_ALIAS_FLAG)

        paint.apply {
            color = mContext.resources.getColor(R.color.colorAccent)
            style = Paint.Style.FILL
        }

    }


    //用于设置padding
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        var position = parent.getChildAdapterPosition(view)
        if (mDecorationCallback.getType(position) == GankItem.ITEM_HEADER) {
            outRect.top = dividerHeight
        }
        Log.d(
            "hezhe",
            "getItemOffsets : left = ${outRect.left} ,top = ${outRect.top} , right = ${outRect.right} , bottom = ${outRect.bottom}"
        )
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)
        var childCount = parent.childCount
        for (index in 0 until childCount) {

            var view = parent.getChildAt(index)
            var position = parent.getChildAdapterPosition(view)
            if (mDecorationCallback.getType(position) != GankItem.ITEM_HEADER) {
                continue
            }
            var bottom = view.top
            var top = bottom - dividerHeight
            var left = view.paddingLeft
            var right = view.paddingRight
            Log.d("hezhe", "ondraw : top =  $top , bottom = $bottom ,left = $left ,right = $right ")
            canvas.drawRect(
                Rect(left + dividerPaddingLeft, top, view.width - right - dividerPaddingRight, bottom),
                paint
            )
        }

    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        var firstVisibleView = parent.getChildAt(0)
        if(firstVisibleView == null) return
        var position = parent.getChildAdapterPosition(firstVisibleView)
        var isHead = mDecorationCallback.getType(position) == GankItem.ITEM_HEADER
        var left = parent.paddingLeft
        var right = firstVisibleView.width - parent.paddingRight
        if (firstVisibleView.bottom <= dividerHeight && isHead) {
            canvas.drawRect(left.toFloat(), 0f, right.toFloat(), firstVisibleView.bottom.toFloat(), paint)
        }else{
            canvas.drawRect(left.toFloat(), 0f, right.toFloat(), dividerHeight.toFloat(), paint)
        }
    }

    fun dp2px(dpValue: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dpValue.toFloat(),
            mContext.resources.displayMetrics
        ).toInt()
    }


    interface DecorationCallback {

        fun getGroupName(position: Int): String

        fun getType(position: Int): Int

    }

}