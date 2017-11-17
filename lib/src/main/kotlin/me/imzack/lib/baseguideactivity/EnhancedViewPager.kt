package me.imzack.lib.baseguideactivity

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class EnhancedViewPager : ViewPager {

    /** 开启或关闭ViewPager的左右滑动 */
    var scrollingEnabled = true

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onTouchEvent(ev: MotionEvent) = scrollingEnabled && super.onTouchEvent(ev)

    override fun onInterceptTouchEvent(ev: MotionEvent) = scrollingEnabled && super.onInterceptTouchEvent(ev)
}
