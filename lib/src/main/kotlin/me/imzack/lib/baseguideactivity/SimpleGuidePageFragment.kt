package me.imzack.lib.baseguideactivity

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
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
        private val ARG_TITLE_COLOR = "title_color"
        private val ARG_DSC = "dsc"
        private val ARG_DSC_COLOR = "dsc_color"
        private val ARG_BTN_TEXT = "btn_text"
        private val ARG_BTN_BG_COLOR = "btn_bg_color"
        private val ARG_BTN_TEXT_COLOR = "btn_text_color"
        private val ARG_BTN_CLICK_LISTENER = "btn_click_listener"

        fun newInstance(
                @DrawableRes imageResId: Int = 0,
                titleText: String? = null,
                @ColorInt titleColor: Int = 0,
                descriptionText: String? = null,
                @ColorInt descriptionColor: Int = 0,
                buttonText: String? = null,
                @ColorInt buttonBackgroundColor: Int = 0,
                @ColorInt buttonTextColor: Int = 0,
                onButtonClickListener: OnButtonClickListener? = null
        ): SimpleGuidePageFragment {
            val fragment = SimpleGuidePageFragment()
            val args = Bundle()
            args.putInt(ARG_IMG, imageResId)
            args.putString(ARG_TITLE, titleText)
            args.putInt(ARG_TITLE_COLOR, titleColor)
            args.putString(ARG_DSC, descriptionText)
            args.putInt(ARG_DSC_COLOR, descriptionColor)
            args.putString(ARG_BTN_TEXT, buttonText)
            args.putInt(ARG_BTN_BG_COLOR, buttonBackgroundColor)
            args.putInt(ARG_BTN_TEXT_COLOR, buttonTextColor)
            args.putSerializable(ARG_BTN_CLICK_LISTENER, onButtonClickListener)
            fragment.arguments = args
            return fragment
        }
    }

    private var mImage: Drawable? = null
    private var mTitleText: String? = null
    private var mTitleColor = 0
    private var mDescriptionText: String? = null
    private var mDescriptionColor = 0
    private var mButtonText: String? = null
    private var mButtonBackgroundColor = 0
    private var mButtonTextColor = 0
    private var mButtonClickListener: OnButtonClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = arguments
        if (args != null) {
            val imgResId = args.getInt(ARG_IMG)
            if (imgResId != 0) {
                mImage = context.getDrawable(imgResId)
            }
            mTitleText = args.getString(ARG_TITLE)
            mTitleColor = args.getInt(ARG_TITLE_COLOR)
            mDescriptionText = args.getString(ARG_DSC)
            mDescriptionColor = args.getInt(ARG_DSC_COLOR)
            mButtonText = args.getString(ARG_BTN_TEXT)
            mButtonBackgroundColor = args.getInt(ARG_BTN_BG_COLOR)
            mButtonTextColor = args.getInt(ARG_BTN_TEXT_COLOR)
            mButtonClickListener = args.getSerializable(ARG_BTN_CLICK_LISTENER) as OnButtonClickListener?
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_simple_guide_page, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (mImage == null) {
            vImage.visibility = View.INVISIBLE
        } else {
            vImage.setImageDrawable(mImage)
        }
        if (mTitleText == null) {
            vTitleText.visibility = View.INVISIBLE
        } else {
            vTitleText.text = mTitleText
            if (mTitleColor != 0) {
                vTitleText.setTextColor(mTitleColor)
            }
        }
        if (mDescriptionText == null) {
            vDescriptionText.visibility = View.INVISIBLE
        } else {
            vDescriptionText.text = mDescriptionText
            if (mDescriptionColor != 0) {
                vDescriptionText.setTextColor(mDescriptionColor)
            }
        }
        if (mButtonText == null) {
            vButton.visibility = View.INVISIBLE
        } else {
            vButton.text = mButtonText
            if (mButtonBackgroundColor != 0) {
                vButton.backgroundTintList = ColorStateList.valueOf(mButtonBackgroundColor)
            }
            if (mButtonTextColor != 0) {
                vButton.setTextColor(mButtonTextColor)
            }
        }
        vButton.setOnClickListener(mButtonClickListener)
    }

    interface OnButtonClickListener : View.OnClickListener, Serializable
}
