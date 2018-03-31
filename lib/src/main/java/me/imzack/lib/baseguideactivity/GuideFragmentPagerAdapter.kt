package me.imzack.lib.baseguideactivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

internal class GuideFragmentPagerAdapter(fm: FragmentManager, private val pageFragmentList: List<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int) = pageFragmentList[position]

    override fun getCount() = pageFragmentList.size
}
