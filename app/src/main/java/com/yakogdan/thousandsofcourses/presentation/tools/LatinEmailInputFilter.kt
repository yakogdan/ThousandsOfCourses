package com.yakogdan.thousandsofcourses.presentation.tools

import android.text.InputFilter
import android.text.Spanned

class LatinEmailInputFilter : InputFilter {
    private val pattern = Regex("[a-zA-Z0-9@._-]+")

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int,
    ): CharSequence? {

        val input = source.subSequence(startIndex = start, endIndex = end)
        return if (pattern.matches(input)) null else ""
    }
}