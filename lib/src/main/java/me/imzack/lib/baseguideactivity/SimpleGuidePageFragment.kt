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

open class SimpleGuidePageFragment : Fragment() {

    companion object {

        private const val ARG_IMG = "img"
        private const val ARG_IMG_TINT = "img_tint"
        private const val ARG_TITLE = "title"
        private const val ARG_DSC = "dsc"
        private const val ARG_BTN_TEXT = "btn_text"
        private const val ARG_BTN_BG_COLOR = "btn_bg_color"
        private const val ARG_BTN_CLICK_LISTENER = "btn_click_listener"

        fun newInstance(
                @DrawableRes imageResId: Int = 0,
                @ColorInt imageTint: Int = 0,
                titleText: CharSequence? = null,
                descriptionText: CharSequence? = null,
                buttonText: CharSequence? = null,
                @ColorInt buttonBackgroundColor: Int = 0,
                onButtonClickListener: OnButtonClickListener? = null
        ): SimpleGuidePageFragment {
            val fragment = SimpleGuidePageFragment()
            val args = Bundle()
            args.putInt(ARG_IMG, imageResId)
            args.putInt(ARG_IMG_TINT, imageTint)
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

    var imageResId = 0
        set(value) {
            if (field == value) return
            field = value
            if (initialized) {
                updateImage()
                arguments!!.putInt(ARG_IMG, value)
            }
        }
    var imageTint = 0
        set(value) {
            if (field == value) return
            field = value
            if (initialized) {
                updateImageTint()
                arguments!!.putInt(ARG_IMG_TINT, value)
            }
        }
    var titleText: CharSequence? = null
        set(value) {
            if (field == value) return
            field = value
            if (initialized) {
                updateTitle()
                arguments!!.putCharSequence(ARG_TITLE, value)
            }
        }
    var descriptionText: CharSequence? = null
        set(value) {
            if (field == value) return
            field = value
            if (initialized) {
                updateDescription()
                arguments!!.putCharSequence(ARG_DSC, value)
            }
        }
    var buttonText: CharSequence? = null
        set(value) {
            if (field == value) return
            field = value
            if (initialized) {
                updateButtonText()
                arguments!!.putCharSequence(ARG_BTN_TEXT, value)
            }
        }
    var buttonBackgroundColor = 0
        set(value) {
            if (field == value) return
            field = value
            if (initialized) {
                updateButtonBackgroundColor()
                arguments!!.putInt(ARG_BTN_BG_COLOR, value)
            }
        }
    var buttonClickListener: OnButtonClickListener? = null
        set(value) {
            if (field == value) return
            field = value
            if (initialized) {
                updateButtonClickListener()
                arguments!!.putSerializable(ARG_BTN_CLICK_LISTENER, value)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = arguments
        if (args != null) {
            imageResId = args.getInt(ARG_IMG)
            imageTint = args.getInt(ARG_IMG_TINT)
            titleText = args.getCharSequence(ARG_TITLE)
            descriptionText = args.getCharSequence(ARG_DSC)
            buttonText = args.getCharSequence(ARG_BTN_TEXT)
            buttonBackgroundColor = args.getInt(ARG_BTN_BG_COLOR)
            buttonClickListener = args.getSerializable(ARG_BTN_CLICK_LISTENER) as OnButtonClickListener?

            initialized = true
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_simple_guide_page, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateImage()
        updateImageTint()
        updateTitle()
        updateDescription()
        updateButtonText()
        updateButtonBackgroundColor()
        updateButtonClickListener()
    }

    private fun updateImage() {
        // 若 imageResId 为 0，不会刷新
        vImage.setImageResource(imageResId)
        updateVisibility(vImage, imageResId != 0)
    }

    private fun updateImageTint() {
        if (imageResId != 0 && imageTint != 0) {
            vImage.imageTintList = ColorStateList.valueOf(imageTint)
        }
    }

    private fun updateTitle() {
        // 若 titleText 为 null，会被置为空字符串
        vTitleText.text = titleText
        updateVisibility(vTitleText, titleText != null)
    }

    private fun updateDescription() {
        vDescriptionText.text = descriptionText
        updateVisibility(vDescriptionText, descriptionText != null)
    }

    private fun updateButtonText() {
        vButton.text = buttonText
        updateVisibility(vButton, buttonText != null)
    }

    private fun updateButtonBackgroundColor() {
        if (buttonText != null && buttonBackgroundColor != 0) {
            vButton.backgroundTintList = ColorStateList.valueOf(buttonBackgroundColor)
        }
    }

    private fun updateButtonClickListener() {
        vButton.setOnClickListener(buttonClickListener)
    }

    private fun updateVisibility(view: View, isVisible: Boolean) {
        view.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
    }

    interface OnButtonClickListener : View.OnClickListener, Serializable
}
