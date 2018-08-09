package net.zackzhang.lib.baseguideactivityexample

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import net.zackzhang.lib.baseguideactivity.BaseGuideActivity
import net.zackzhang.lib.baseguideactivity.SimpleGuidePageFragment

class ExampleGuideActivity : BaseGuideActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBackgroundColor(Color.DKGRAY)
        setStartButtonColor(Color.CYAN)
        setEndButtonColor(Color.CYAN)
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)

        // 在这里为 Fragment 添加监听事件，以防止 Activity 重建后监听事件丢失
        if (fragment.tag == getPageFragmentTag(2)) {
            (fragment as SimpleGuidePageFragment).buttonClickListener = { Toast.makeText(this@ExampleGuideActivity, "You clicked AGREE button", Toast.LENGTH_SHORT).show() }
        }
    }

    override fun getPageFragmentList() = listOf(
            SimpleGuidePageFragment.Builder()
                    .setImage(R.drawable.ic_android_black_24dp, Color.MAGENTA)
                    .setTitle("Welcome")
                    .setDescription("This is a description")
                    .build(),
            SpecialGuidePageFragment.newInstance(),
            SimpleGuidePageFragment.Builder()
                    .setImage(R.drawable.ic_android_black_24dp, Color.GREEN)
                    .setTitle("Enjoy")
                    .setDescription("Agree following licence to continue")
                    .setButton("Agree", Color.BLUE)
                    .build()
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