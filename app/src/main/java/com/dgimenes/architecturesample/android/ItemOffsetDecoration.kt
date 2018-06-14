package com.dgimenes.architecturesample.android

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.dgimenes.architecturesample.R

class ItemOffsetDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val decorationHeight = context.resources.getDimensionPixelSize(R.dimen.cards_margin)

    override fun getItemOffsets(outRect: Rect, view: View?, parent: RecyclerView?, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view!!, parent, state)

        if (parent != null) {
            val itemPosition = parent.getChildAdapterPosition(view)

            if (itemPosition == 0) {
                outRect.top = decorationHeight
            }

            outRect.bottom = decorationHeight
        }
    }

}
