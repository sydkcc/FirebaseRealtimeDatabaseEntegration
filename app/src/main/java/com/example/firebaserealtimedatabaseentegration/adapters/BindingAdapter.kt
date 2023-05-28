package com.example.firebasedbentegration.adapters

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.FutureTarget
import com.bumptech.glide.request.RequestFutureTarget
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}



//@BindingAdapter("aspectRatio")
//fun setAspectRatio(imageView: ImageView, aspectRatio: Float) {
//    val layoutParams = imageView.layoutParams as ConstraintLayout.LayoutParams
//    layoutParams.dimensionRatio = "H,$aspectRatio"
//    imageView.layoutParams = layoutParams
//}

@BindingAdapter("aspectRatio")
fun setAspectRatio(imageView: ImageView, aspectRatio: String) {
    val aspectRatioParts = aspectRatio.split(":")
    if (aspectRatioParts.size == 2) {
        val ratioWidth = aspectRatioParts[0].toFloatOrNull()
        val ratioHeight = aspectRatioParts[1].toFloatOrNull()
        if (ratioWidth != null && ratioHeight != null && ratioHeight != 0f) {
            imageView.post {
                val params = imageView.layoutParams
                val viewWidth = imageView.width.toFloat()
                params.height = (viewWidth / (ratioWidth / ratioHeight)).toInt()
                imageView.layoutParams = params
            }
        }
    }
}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .override(RequestFutureTarget.SIZE_ORIGINAL)
            .into(view)
    }
}
