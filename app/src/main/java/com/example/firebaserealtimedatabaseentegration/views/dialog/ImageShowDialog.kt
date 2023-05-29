package com.example.firebaserealtimedatabaseentegration.views.dialog

import android.os.Bundle
import android.view.ViewGroup
import app.presentation.base.view.BaseDialogHelper
import app.presentation.base.view.PopupActionType
import app.presentation.extension.load
import com.example.firebaserealtimedatabaseentegration.R
import com.example.firebaserealtimedatabaseentegration.databinding.PopupImageShowBinding

class ImageShowDialog : BaseDialogHelper<PopupImageShowBinding, PopupActionType>() {
    private var image: String? = null

    override fun getContentView(): Int = R.layout.popup_image_show

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            image = it.getString(IMAGE) as String?
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            it.window?.setLayout(width, height)
        }
    }

    override fun subScribe() {
        super.subScribe()
        dataBinding?.apply {
            this.btnClose.setOnClickListener { dismiss() }
            image?.let {
                this.showImage.load(it)
            }
        }
    }

    companion object {
        const val TAG = "ShowImageDialog"
        private const val IMAGE = "IMAGE"
        fun newInstance(image: String?) =
            ImageShowDialog().apply {
                arguments =
                    Bundle().apply {
                        putString(IMAGE, image)
                    }
            }
    }
}