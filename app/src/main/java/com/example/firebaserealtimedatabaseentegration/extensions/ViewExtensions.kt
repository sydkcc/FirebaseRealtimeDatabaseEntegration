package com.example.firebaserealtimedatabaseentegration.extensions

import android.app.Activity
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.app.ActivityCompat.startPostponedEnterTransition
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.firebaserealtimedatabaseentegration.R

fun ImageView.load(
    url: String?,
    activity: Activity? = null,
) {
    val requestOptions = RequestOptions().apply {
    }
    val glideRequest = Glide
        .with(context)
        .load(url)
        .placeholder(R.color.white)
        .dontAnimate()

    activity?.let {
        glideRequest
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean,
                ): Boolean {
                    startPostponedEnterTransition(it)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean,
                ): Boolean {
                    startPostponedEnterTransition(it)
                    return false
                }
            })
    }

    glideRequest.into(this)
}