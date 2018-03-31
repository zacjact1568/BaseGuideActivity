package me.imzack.lib.baseguideactivity

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

// 禁止滑动翻页的 ViewPager
internal class GuideViewPager : ViewPager {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onTouchEvent(ev: MotionEvent) = false

    override fun onInterceptTouchEvent(ev: MotionEvent) = false
}
