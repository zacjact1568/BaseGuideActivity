package me.imzack.lib.baseguideactivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_base_guide.*

abstract class BaseGuideActivity : AppCompatActivity() {

    // 不能直接初始化，会有警告
    private val mGuidePagerAdapter by lazy { GuidePagerAdapter(supportFragmentManager, provideFragmentList()) }

    private var mLastBackKeyPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_base_guide)

        vGuidePager.adapter = mGuidePagerAdapter
        vGuidePager.scrollingEnabled = false
        vGuidePager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                onPageSelected(position == 0, position == mGuidePagerAdapter.count - 1)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        vStartButton.setOnClickListener {
            val currentPage = vGuidePager.currentItem
            if (currentPage != 0) {
                vGuidePager.currentItem = currentPage - 1
            }
        }

        vEndButton.setOnClickListener {
            val currentPage = vGuidePager.currentItem
            if (currentPage != mGuidePagerAdapter.count - 1) {
                //不是最后一页
                vGuidePager.currentItem = currentPage + 1
            } else {
                //最后一页
                onLastPageTurned()
            }
        }

        onPageSelected(true, mGuidePagerAdapter.count == 1)
    }

    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - mLastBackKeyPressedTime < 1500) {
            onBackPressedTwice()
        } else {
            mLastBackKeyPressedTime = currentTime
            onBackPressedOnce()
        }
    }

    /** 必须重写此函数提供引导页列表 */
    abstract fun provideFragmentList(): List<Fragment>

    /** 触摸一次后退键执行的函数，默认结束引导 */
    open fun onBackPressedOnce() {
        super.onBackPressed()
    }

    /** 快速触摸两次后退键执行的函数，默认结束引导 */
    open fun onBackPressedTwice() {
        super.onBackPressed()
    }

    /** 翻过最后一页执行的函数，默认结束引导 */
    open fun onLastPageTurned() {
        super.onBackPressed()
    }

    /** 改变背景颜色，默认无色 */
    fun setBackgroundColor(color: Int) {
        vContentLayout.setBackgroundColor(color)
    }

    /** 改变左边按钮的背景颜色，默认黑色 */
    fun setStartButtonColor(color: Int) {
        vStartButton.setFillColor(color)
    }

    /** 改变右边按钮的背景颜色，默认黑色 */
    fun setEndButtonColor(color: Int) {
        vEndButton.setFillColor(color)
    }

    private fun onPageSelected(isFirstPage: Boolean, isLastPage: Boolean) {
        vStartButton.visibility = if (isFirstPage) View.GONE else View.VISIBLE
        vEndButton.visibility = if (isFirstPage && isLastPage) View.GONE else View.VISIBLE
        vEndButton.setInnerIcon(getDrawable(if (isLastPage) R.drawable.ic_check_black_24dp else R.drawable.ic_arrow_forward_black_24dp))
    }
}