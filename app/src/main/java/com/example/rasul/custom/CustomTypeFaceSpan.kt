package com.example.rasul.custom

import android.content.Context
import android.graphics.Paint
import android.text.TextPaint
import android.text.style.RelativeSizeSpan
import android.util.TypedValue

class CustomTypeFaceSpan(private val size: Int, private val context: Context) : RelativeSizeSpan(size.toFloat()) {

    override fun updateDrawState(ds: TextPaint) {
        applyCustomTypeFace(ds, size.toFloat())
    }

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTypeFace(paint, size.toFloat())
    }

    private fun applyCustomTypeFace(paint: Paint, size: Float) {
        val textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, context.resources.displayMetrics)
        paint.textSize = textSize
        paint.clearShadowLayer()
    }
}