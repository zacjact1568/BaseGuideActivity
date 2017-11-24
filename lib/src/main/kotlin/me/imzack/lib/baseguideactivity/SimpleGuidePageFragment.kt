package me.imzack.lib.baseguideactivity

import android.content.res.ColorStateList
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_simple_guide_page.*
import java.io.Serializable

class SimpleGuidePageFragment : Fragment() {

    companion object {

        private val ARG_IMG = "img"
        private val ARG_TITLE = "title"
        private val ARG_DSC = "dsc"
        private val ARG_BTN_TEXT = "btn_text"
        private val ARG_BTN_BG_COLOR = "btn_bg_color"
        private val ARG_BTN_CLICK_LISTENER = "btn_click_listener"

        fun newInstance(
                @DrawableRes imageResId: Int = 0,
                titleText: CharSequence? = null,
                descriptionText: CharSequence? = null,
                buttonText: CharSequence? = null,
                @ColorInt buttonBackgroundColor: Int = 0,
                onButtonClickListener: OnButtonClickListener? = null
        ): SimpleGuidePageFragment {
            val fragment = SimpleGuidePageFragment()
            val args = Bundle()
            args.putInt(ARG_IMG, imageResId)
            args.putCharSequence(ARG_TITLE, titleText)
            args.putCharSequence(ARG_DSC, descriptionText)
            args.putCharSequence(ARG_BTN_TEXT, buttonText)
            args.putInt(ARG_BTN_BG_COLOR, buttonBackgroundColor)
            args.putSerializable(ARG_BTN_CLICK_LISTENER, onButtonClickListener)
            fragment.arguments = args
            return fragment
        }
    }

    private var initialized = false

    var mImageResId = 0
        set(value) {
            if (field == value) return
            field = value
            if (initialized) {
                updateImage()
                arguments.putInt(ARG_IMG, value)
            }
        }
    var mTitleText: CharSequence? = null
        set(value) {
            if (field == value) return
            field = value
            if (initialized) {
                updateTitle()
                arguments.putCharSequence(ARG_TITLE, value)
            }
        }
    var mDescriptionText: CharSequence? = null
        set(value) {
            if (field == value) return
            field = value
            if (initialized) {
                updateDescription()
                arguments.putCharSequence(ARG_DSC, value)
            }
        }
    var mButtonText: CharSequence? = null
        set(value) {
            if (field == value) return
            field = value
            if (initialized) {
                updateButtonText()
                arguments.putCharSequence(ARG_BTN_TEXT, value)
            }
        }
    var mButtonBackgroundColor = 0
        set(value) {
            if (field == value) return
            field = value
            if (initialized) {
                updateButtonBackgroundColor()
                arguments.putInt(ARG_BTN_BG_COLOR, value)
            }
        }
    var mButtonClickListener: OnButtonClickListener? = null
        set(value) {
            if (field == value) return
            field = value
            if (initialized) {
                updateButtonClickListener()
                arguments.putSerializable(ARG_BTN_CLICK_LISTENER, value)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = arguments
        if (args != null) {
            mImageResId = args.getInt(ARG_IMG)
            mTitleText = args.getCharSequence(ARG_TITLE)
            mDescriptionText = args.getCharSequence(ARG_DSC)
            mButtonText = args.getCharSequence(ARG_BTN_TEXT)
            mButtonBackgroundColor = args.getInt(ARG_BTN_BG_COLOR)
            mButtonClickListener = args.getSerializable(ARG_BTN_CLICK_LISTENER) as OnButtonClickListener?

            initialized = true
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_simple_guide_page, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateImage()
        updateTitle()
        updateDescription()
        updateButtonText()
        updateButtonBackgroundColor()
        updateButtonClickListener()
    }

    private fun updateImage() {
        if (mImageResId == 0) {
            vImage.visibility = View.INVISIBLE
        } else {
            vImage.setImageResource(mImageResId)
        }
    }

    private fun updateTitle() {
        if (mTitleText == null) {
            vTitleText.visibility = View.INVISIBLE
        } else {
            vTitleText.text = mTitleText
        }
    }

    private fun updateDescription() {
        if (mDescriptionText == null) {
            vDescriptionText.visibility = View.INVISIBLE
        } else {
            vDescriptionText.text = mDescriptionText
        }
    }

    private fun updateButtonText() {
        if (mButtonText == null) {
            vButton.visibility = View.INVISIBLE
        } else {
            vButton.text = mButtonText
        }
    }

    private fun updateButtonBackgroundColor() {
        if (mButtonText != null && mButtonBackgroundColor != 0) {
            vButton.backgroundTintList = ColorStateList.valueOf(mButtonBackgroundColor)
        }
    }

    private fun updateButtonClickListener() {
        vButton.setOnClickListener(mButtonClickListener)
    }

    interface OnButtonClickListener : View.OnClickListener, Serializable
}
