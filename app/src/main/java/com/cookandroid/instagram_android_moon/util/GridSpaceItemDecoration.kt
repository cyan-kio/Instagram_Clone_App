package com.cookandroid.instagram_android_moon.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpaceItemDecoration(private val spanCount: Int, private val space: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount + 1      // 1부터 시작
        val totalSpace = space * (spanCount - 1)   // 총 여백
        val eachSpace = totalSpace / spanCount   // span에 따라 부여될 각 item의 여백

        // Start Column -> Right Space
        if (column == 1) outRect.right = eachSpace
        // End Colomn -> Left Space
        else if (column == spanCount) outRect.left = eachSpace
        // Center Colomns -> Both Sides Space
        else {
            outRect.left = eachSpace/2
            outRect.right = eachSpace/2
        }

        // Positions Excepted First Row
        if (position >= spanCount) {
            outRect.top = space
        }

    }
}