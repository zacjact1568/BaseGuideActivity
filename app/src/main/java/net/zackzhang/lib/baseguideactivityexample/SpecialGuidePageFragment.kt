package net.zackzhang.lib.baseguideactivityexample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import net.zackzhang.lib.baseguideactivity.SimpleGuidePageFragment

class SpecialGuidePageFragment : SimpleGuidePageFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageResId = R.mipmap.ic_launcher
        buttonText = "Test"
        buttonClickListener = { Toast.makeText(context, "Test passed", Toast.LENGTH_SHORT).show() }
    }
}