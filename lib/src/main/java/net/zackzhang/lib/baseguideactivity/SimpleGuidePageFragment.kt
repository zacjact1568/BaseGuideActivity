package net.zackzhang.lib.baseguideactivity

import android.content.res.ColorStateList
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_simple_guide_page.*

open class SimpleGuidePageFragment : Fragment() {

    companion object {

        private const val ARG_IMAGE = "image"
        private const val ARG_IMAGE_TINT = "image_tint"
        private const val ARG_TITLE = "title"
        private const val ARG_DESCRIPTION = "description"
        private const val ARG_BUTTON_TEXT = "button_text"
        private const val ARG_BUTTON_BACKGROUND_COLOR = "button_background_color"
    }

    private var initialized = false

    var imageResId = 0
        set(value) {
            if (field == value) return
            field = value
            if (initialized) {
                updateImage()
                arguments!!.putInt(ARG_IMAGE, value)
            }
        }
    var imageTint = 0
        set(value) {
            if (field == value) return
            field = value
            if (initialized) {
                updateImageTint()
                arguments!!.putInt(ARG_IMAGE_TINT, value)
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
                arguments!!.putCharSequence(ARG_DESCRIPTION, value)
            }
        }
    var buttonText: CharSequence? = null
        set(value) {
            if (field == value) return
            field = value
            if (initialized) {
                updateButtonText()
                arguments!!.putCharSequence(ARG_BUTTON_TEXT, value)
            }
        }
    var buttonBackgroundColor = 0
        set(value) {
            if (field == value) return
            field = value
            if (initialized) {
                updateButtonBackgroundColor()
                arguments!!.putInt(ARG_BUTTON_BACKGROUND_COLOR, value)
            }
        }
    var buttonClickListener: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 若无值，返回 0
        imageResId = arguments!!.getInt(ARG_IMAGE)
        imageTint = arguments!!.getInt(ARG_IMAGE_TINT)
        // 若无值，返回 null
        titleText = arguments!!.getCharSequence(ARG_TITLE)
        descriptionText = arguments!!.getCharSequence(ARG_DESCRIPTION)
        buttonText = arguments!!.getCharSequence(ARG_BUTTON_TEXT)
        buttonBackgroundColor = arguments!!.getInt(ARG_BUTTON_BACKGROUND_COLOR)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_simple_guide_page, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialized = true

        updateImage()
        updateImageTint()
        updateTitle()
        updateDescription()
        updateButtonText()
        updateButtonBackgroundColor()

        vButton.setOnClickListener { buttonClickListener?.invoke() }
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

    private fun updateVisibility(view: View, isVisible: Boolean) {
        view.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
    }

    class Builder {

        private val args = Bundle()

        fun setImage(@DrawableRes resId: Int, @ColorInt tint: Int = 0): Builder {
            args.putInt(ARG_IMAGE, resId)
            args.putInt(ARG_IMAGE_TINT, tint)
            return this
        }

        fun setTitle(title: CharSequence): Builder {
            args.putCharSequence(ARG_TITLE, title)
            return this
        }

        fun setDescription(description: CharSequence): Builder {
            args.putCharSequence(ARG_DESCRIPTION, description)
            return this
        }

        fun setButton(text: CharSequence, @ColorInt backgroundColor: Int = 0): Builder {
            args.putCharSequence(ARG_BUTTON_TEXT, text)
            args.putInt(ARG_BUTTON_BACKGROUND_COLOR, backgroundColor)
            return this
        }

        fun build(): SimpleGuidePageFragment {
            val fragment = SimpleGuidePageFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
