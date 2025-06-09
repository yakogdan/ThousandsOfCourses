package com.yakogdan.thousandsofcourses.presentation.adapters.itemdecoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yakogdan.thousandsofcourses.presentation.tools.convertDpToPixels

class CourseItemDecoration(
    private val paddingBottomDp: Int,
    private val context: Context,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        outRect.bottom = paddingBottomDp.convertDpToPixels(context)
    }
}