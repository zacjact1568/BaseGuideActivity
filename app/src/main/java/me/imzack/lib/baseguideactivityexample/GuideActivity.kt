package me.imzack.lib.baseguideactivityexample

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import me.imzack.lib.baseguideactivity.BaseGuideActivity
import me.imzack.lib.baseguideactivity.SimpleGuidePageFragment

class GuideActivity : BaseGuideActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBackgroundColor(Color.DKGRAY)
        setStartButtonColor(Color.CYAN)
        setEndButtonColor(Color.CYAN)
    }

    override fun provideFragmentList() = listOf(
            SimpleGuidePageFragment.newInstance(
                    R.drawable.ic_android_black_24dp,
                    Color.MAGENTA,
                    "Welcome",
                    "This is a description"
            ),
            SpecialGuidePageFragment.newInstance(),
            SimpleGuidePageFragment.newInstance(
                    R.drawable.ic_android_black_24dp,
                    Color.GREEN,
                    "Enjoy",
                    "Agree following licence to continue",
                    "Agree",
                    Color.BLUE,
                    object : SimpleGuidePageFragment.OnButtonClickListener {
                        override fun onClick(v: View) {
                            Toast.makeText(this@GuideActivity, "You clicked AGREE button", Toast.LENGTH_SHORT).show()
                        }
                    }
            )
    )

    override fun onBackPressedOnce() {
        Toast.makeText(this, "Click once again to exit", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressedTwice() {
        finish()
    }

    override fun onLastPageTurned() {
        finish()
    }
}