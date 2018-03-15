package me.imzack.lib.baseguideactivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

internal class GuidePagerAdapter(fm: FragmentManager, private val fragmentList: List<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int) = fragmentList[position]

    override fun getCount() = fragmentList.size
}
