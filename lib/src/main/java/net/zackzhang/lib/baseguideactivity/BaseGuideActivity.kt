package net.zackzhang.lib.baseguideactivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_base_guide.*

abstract class BaseGuideActivity : AppCompatActivity() {

    // 不能直接初始化，会有警告
    private val guidePagerAdapter by lazy { GuideFragmentPagerAdapter(supportFragmentManager, getPageFragmentList()) }

    private var lastBackKeyPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_base_guide)

        vPager.adapter = guidePagerAdapter
        vPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                onPageSelected(position == 0, position == guidePagerAdapter.count - 1)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        vStartButton.setOnClickListener {
            val currentPage = vPager.currentItem
            if (currentPage != 0) {
                vPager.currentItem = currentPage - 1
            }
        }

        vEndButton.setOnClickListener {
            val currentPage = vPager.currentItem
            if (currentPage != guidePagerAdapter.count - 1) {
                //不是最后一页
                vPager.currentItem = currentPage + 1
            } else {
                //最后一页
                onLastPageTurned()
            }
        }

        onPageSelected(true, guidePagerAdapter.count == 1)
    }

    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastBackKeyPressedTime < 1500) {
            onBackPressedTwice()
        } else {
            lastBackKeyPressedTime = currentTime
            onBackPressedOnce()
        }
    }

    /** 重写此函数提供引导页 Fragment 列表 */
    abstract fun getPageFragmentList(): List<Fragment>

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

    /** 获取引导页 Fragment 的 tag */
    // Fragment 的 tag 写死在了 FragmentPagerAdapter 中
    // 其中，获取 vPager 的 id 不能使用 vPager.id，因为在重建 activity 过程中，vPager 会为 null，因此直接使用常量 R.id.vPager
    fun getPageFragmentTag(position: Int) = "android:switcher:${R.id.vPager}:$position"

    private fun onPageSelected(isFirstPage: Boolean, isLastPage: Boolean) {
        vStartButton.visibility = if (isFirstPage) View.GONE else View.VISIBLE
        vEndButton.visibility = if (isFirstPage && isLastPage) View.GONE else View.VISIBLE
        vEndButton.setInnerIcon(getDrawable(if (isLastPage) R.drawable.ic_check_black_24dp else R.drawable.ic_arrow_forward_black_24dp))
    }
}