package me.imzack.lib.baseguideactivityexample

import android.os.Bundle
import android.view.View
import me.imzack.lib.baseguideactivity.SimpleGuidePageFragment

class SpecialGuidePageFragment : SimpleGuidePageFragment() {

    companion object {

        fun newInstance(): SpecialGuidePageFragment {
            val fragment = SpecialGuidePageFragment()
            fragment.arguments = Bundle()
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageResId = R.mipmap.ic_launcher
    }
}