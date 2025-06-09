package com.yakogdan.thousandsofcourses.data.tools

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.toDate(): Date {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.parse(this)
}

fun Date.toStringDate(): String {
    val dateFormat = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
    return dateFormat.format(this)
}