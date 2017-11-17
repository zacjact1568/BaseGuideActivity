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
                    R.mipmap.ic_launcher,
                    "Welcome",
                    Color.WHITE,
                    "This is a description",
                    Color.WHITE
            ),
            SimpleGuidePageFragment.newInstance(
                    R.mipmap.ic_launcher_round,
                    "Enjoy",
                    Color.WHITE,
                    "Agree following licence to continue",
                    Color.WHITE,
                    "Agree",
                    Color.BLUE,
                    Color.WHITE,
                    object : SimpleGuidePageFragment.OnButtonClickListener {
                        override fun onClick(v: View) {
                            Toast.makeText(this@GuideActivity, "You clicked AGREE button", Toast.LENGTH_SHORT).show()
                        }
                    }
            )
    )

    override fun onBackPressedOnce() {
        Toast.makeText(this@GuideActivity, "Click once again to exit", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressedTwice() {
        finish()
    }

    override fun onLastPageTurned() {
        finish()
    }
}