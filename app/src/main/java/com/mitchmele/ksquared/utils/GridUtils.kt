package com.mitchmele.ksquared.utils

import android.content.Context
import android.util.DisplayMetrics


class GridUtils {

    companion object {
        fun calculateNoOfColumns(context: Context, columnWidthDp: Float): Int {
            val displayMetrics: DisplayMetrics = context.resources.displayMetrics
            val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
            return (screenWidthDp / columnWidthDp + 0.5).toInt()
        }
    }
}