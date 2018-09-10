package com.example.sergey.codeforcesapplication.feature.widget

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.TextView

class ScrollingTextView(
        context: Context,
        attrs: AttributeSet? = null
) : TextView(context, attrs) {

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        if (focused) {
            super.onFocusChanged(focused, direction, previouslyFocusedRect)
        }
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        if (hasWindowFocus) {
            super.onWindowFocusChanged(hasWindowFocus)
        }
    }

    override fun isFocused(): Boolean {
        return true
    }
}