package com.yakogdan.thousandsofcourses.presentation.tools

import android.content.Context
import android.util.TypedValue

fun Int.convertDpToPixels(context: Context) = TypedValue.applyDimension(
    /* unit = */ TypedValue.COMPLEX_UNIT_DIP,
    /* value = */ this.toFloat(),
    /* metrics = */ context.resources.displayMetrics,
).toInt()