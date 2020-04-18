package com.mitchmele.ksquared.model

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout

class ClickableFrameLayout : FrameLayout {
    private var mOnClickListener: OnClickListener? = null
    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        mOnClickListener = l
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return mOnClickListener != null
    }

    // Standard constructors — just pass everything
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)
}